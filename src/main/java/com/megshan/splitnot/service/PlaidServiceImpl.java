package com.megshan.splitnot.service;

import com.plaid.client.PlaidPublicClient;
import com.plaid.client.response.Institution;
import com.plaid.client.response.InstitutionsResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by shantanu on 4/12/17.
 */

@Slf4j
@Component
@Getter
@Setter
public class PlaidServiceImpl implements PlaidService {

    @Autowired
    private PlaidPublicClient plaidPublicClient;

    public List<Institution> getAllInstitutions() {
        InstitutionsResponse institutionsResponse = plaidPublicClient.getAllInstitutions();

        List<Institution> institutions = Arrays.asList(institutionsResponse.getInstitutions());
        log.info("Retrieved " + institutions.size() + " institutions");

        return institutions;
    }
}
