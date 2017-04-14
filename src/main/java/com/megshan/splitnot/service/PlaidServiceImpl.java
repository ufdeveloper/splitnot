package com.megshan.splitnot.service;

import com.plaid.client.PlaidClient;
import com.plaid.client.request.ItemPublicTokenExchangeRequest;
import com.plaid.client.response.ItemPublicTokenExchangeResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shantanu on 4/12/17.
 */

@Slf4j
@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "plaid.client")
public class PlaidServiceImpl implements PlaidService {

    @Autowired
    private PlaidClient plaidClient;

    private String publicKey;

    public List<String> getAllInstitutions() {

        log.info("Retrieved institutions");

        return new ArrayList<>();
    }

    public String getAccessToken() {
        try {
            Response<ItemPublicTokenExchangeResponse> response
                = plaidClient.service().itemPublicTokenExchange(new ItemPublicTokenExchangeRequest(publicKey)).execute();
            String accessToken = response.body().getAccessToken();
            return accessToken;
        } catch (IOException ioe) {
            log.error("Error retrieving access token");
            return null;
        }

    }
}
