package com.megshan.splitnot.service;

import com.plaid.client.PlaidClient;
import com.plaid.client.internal.gson.Optional;
import com.plaid.client.request.ItemCreateRequest;
import com.plaid.client.request.ItemPublicTokenExchangeRequest;
import com.plaid.client.request.common.Product;
import com.plaid.client.response.ItemCreateResponse;
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
    public String createAccessToken(String publicToken) throws IOException{

        //get access token for publicToken
        Response<ItemPublicTokenExchangeResponse> response =
                plaidClient.service().itemPublicTokenExchange(new ItemPublicTokenExchangeRequest(publicToken)).execute();

        if(!response.isSuccessful()) {
            log.error("Error retrieving access token for publicToken=" + publicToken
                    + ", errorMessage=" + response.errorBody().string());
            return null;
        }

        String accessToken = response.body().getAccessToken();
        log.info("Retrieved accessToken=" + accessToken + " for publicToken=" + publicToken);
        return accessToken;
    }

    @Override
    public void createItem() throws IOException{
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE, -2);

        Calendar endDate = Calendar.getInstance();
        startDate.add(Calendar.DATE, -1);

        String webhookUrl = WebHookServiceImpl.WEBHOOK_DOMAIN + WebHookServiceImpl.WEBHOOK_URL_PART;
        ItemCreateRequest request = new ItemCreateRequest(
                TARTAN_BANK_INSTITUTION_ID,
                Collections.singletonList(Product.TRANSACTIONS))
                .withCredentials("username", "user_good")
                .withCredentials("password", "pass_good")
                .withOptionWebhook(webhookUrl)
                .withOptionAwaitResults(false)
                .withOptionStartDate(startDate.getTime())
                .withOptionEndDate(endDate.getTime());

        Response<ItemCreateResponse> response = plaidClient.service().itemCreate(request).execute();
        log.info("Item Create response=" + response.body().toString());

    }

    @Override
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String getAccessToken() {
        return this.accessToken;
    }
}
