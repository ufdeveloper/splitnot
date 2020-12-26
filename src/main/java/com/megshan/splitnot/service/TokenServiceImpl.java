package com.megshan.splitnot.service;

import com.megshan.splitnot.exceptions.TokenServiceException;
import com.plaid.client.PlaidClient;
import com.plaid.client.internal.gson.Optional;
import com.plaid.client.request.ItemPublicTokenExchangeRequest;
import com.plaid.client.request.common.Product;
import com.plaid.client.response.ItemPublicTokenExchangeResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shantanu on 4/14/17.
 */
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    public static final String TARTAN_BANK_INSTITUTION_ID = "ins_109511";

    // we have this field only for the purpose of the quickstart demo.
    // In a real application, this would be stored in persistent storage for that user.
    private String accessToken;

    @Autowired
    private PlaidClient plaidClient;

    @Override
    public Map<String, String> exchangePublicToken(String publicToken) {

        Response<ItemPublicTokenExchangeResponse> response = null;
        try {
            //get access token for publicToken
            response = plaidClient.service().itemPublicTokenExchange(new ItemPublicTokenExchangeRequest(publicToken)).execute();
        } catch (IOException ioe) {

            log.error("Error exchanging publicToken for accessToken, publicToken=" + publicToken, ", exception=" + ioe);
            throw new TokenServiceException(ioe.getMessage());
        }

        if(!response.isSuccessful()) {

            String tokenExchangeError;

            try {
                tokenExchangeError = response.errorBody().string();
            } catch (IOException ioe) {
                log.error("Error retrieving error message for public token exchange failure, publicToken=" + publicToken
                        + ", exception=" + ioe);
                throw new TokenServiceException(ioe.getMessage());
            }

            log.error("Error retrieving access token for publicToken=" + publicToken
                    + ", exception=" + tokenExchangeError);
            throw new TokenServiceException(tokenExchangeError);
        }

        String accessToken = response.body().getAccessToken();
        String itemId = response.body().getItemId();
        log.info("Retrieved accessToken=" + accessToken + "and itemId=" + itemId + " for publicToken=" + publicToken);

        Map<String, String> tokenExchangeResponse = new HashMap<>();
        tokenExchangeResponse.put("accessToken", accessToken);
        tokenExchangeResponse.put("itemId", itemId);
        return tokenExchangeResponse;
    }

//    @Override
//    public void createItem() throws IOException{
//        Calendar startDate = Calendar.getInstance();
//        startDate.add(Calendar.DATE, -2);
//
//        Calendar endDate = Calendar.getInstance();
//        startDate.add(Calendar.DATE, -1);
//
//        String webhookUrl = WebHookServiceImpl.WEBHOOK_DOMAIN + WebHookServiceImpl.WEBHOOK_URL_PART;
//        ItemCreateRequest request = new ItemCreateRequest(
//                TARTAN_BANK_INSTITUTION_ID,
//                Collections.singletonList(Product.TRANSACTIONS))
//                .withCredentials("username", "user_good")
//                .withCredentials("password", "pass_good")
//                .withOptionWebhook(webhookUrl)
//                .withOptionAwaitResults(false)
//                .withOptionStartDate(startDate.getTime())
//                .withOptionEndDate(endDate.getTime());
//
//        Response<ItemCreateResponse> response = plaidClient.service().itemCreate(request).execute();
//        log.info("Item Create response=" + response.body().toString());
//
//    }

    @Override
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String getAccessToken() {
        return this.accessToken;
    }
}
