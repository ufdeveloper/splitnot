package com.megshan.splitnot.service;

import com.megshan.splitnot.domain.UserInstitution;
import com.plaid.client.PlaidClient;
import com.plaid.client.request.AuthGetRequest;
import com.plaid.client.response.AuthGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by shantanu on 4/14/17.
 */
@Slf4j
@Service
public class UserInstitutionServiceImpl implements UserInstitutionService {

    @Autowired
    private PlaidClient plaidClient;

    @Override
    public AuthGetResponse addInstitutionForUser(String userKey, String accessToken) throws IOException{
        Response<AuthGetResponse> response =
                plaidClient.service().authGet(new AuthGetRequest(accessToken)).execute();

        if(!response.isSuccessful()) {
            log.error("Error retrieving auth for userKey=" + userKey
                    + ", errorMessage=" + response.errorBody().string());
            return null;
        }

        AuthGetResponse authGetResponse = response.body();
        log.info("Retrieved authGetResponse=" + authGetResponse);
        return authGetResponse;
    }

    @Override
    public UserInstitution getUserInsititution() {
        return null;
    }
}
