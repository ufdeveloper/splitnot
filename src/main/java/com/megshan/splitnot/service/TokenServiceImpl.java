package com.megshan.splitnot.service;

import com.plaid.client.PlaidClient;
import com.plaid.client.internal.gson.Optional;
import com.plaid.client.request.ItemPublicTokenExchangeRequest;
import com.plaid.client.response.ItemPublicTokenExchangeResponse;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by shantanu on 4/14/17.
 */
@Slf4j
@Setter
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private PlaidClient plaidClient;

    @Override
    public void createAccessToken(String userKey, String publicToken) throws IOException{

        //get access token for publicToken
        Response<ItemPublicTokenExchangeResponse> response =
                plaidClient.service().itemPublicTokenExchange(new ItemPublicTokenExchangeRequest(publicToken)).execute();

        if(response.isSuccessful()) {
            log.info("Retrieved accessToken=" + response.body().getAccessToken() + " for userKey=" + userKey);
        } else {
            log.error("Error retrieving access token for userKey=" + userKey
                    + ", errorMessage=" + response.errorBody().string());
        }
    }
}
