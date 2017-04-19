package com.megshan.splitnot.service;

import com.plaid.client.PlaidClient;
import com.plaid.client.request.InstitutionsGetByIdRequest;
import com.plaid.client.request.InstitutionsGetRequest;
import com.plaid.client.response.Institution;
import com.plaid.client.response.InstitutionsGetByIdResponse;
import com.plaid.client.response.InstitutionsGetResponse;
import com.plaid.client.response.ItemStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by shantanu on 4/19/17.
 */
@Slf4j
@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    private PlaidClient plaidClient;

    @Override
    public Institution getInstitutionById(String institutionId) throws IOException {
        Response<InstitutionsGetByIdResponse> response =
                plaidClient.service().institutionsGetById(new InstitutionsGetByIdRequest(institutionId))
                .execute();

        if(!response.isSuccessful()) {
            log.error("Error retrieving institution with institutionId=" + institutionId
                    + ", errorMessage=" + response.errorBody().string());
            return null;
        }

        Institution institution = response.body().getInstitution();
        log.info("Retrieved institutionId=" + institutionId);

        return institution;
    }
}
