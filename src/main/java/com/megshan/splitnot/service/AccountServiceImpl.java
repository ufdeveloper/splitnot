package com.megshan.splitnot.service;

import com.plaid.client.PlaidClient;
import com.plaid.client.request.AccountsGetRequest;
import com.plaid.client.response.Account;
import com.plaid.client.response.AccountsGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

/**
 * Created by shantanu on 4/12/17.
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private PlaidClient plaidClient;

    @Autowired
    private TokenService tokenService;

    @Override
    public List<Account> getAccounts() throws IOException{

        Response<AccountsGetResponse> response = plaidClient.service().accountsGet(
                new AccountsGetRequest(tokenService.getAccessToken()))
                .execute();

        if(!response.isSuccessful()) {
            log.error("Error retrieving accounts with accessToken=" + tokenService.getAccessToken()
                    + ", errorMessage=" + response.errorBody().string());
            return null;
        }

        List<Account> accounts = response.body().getAccounts();
        if(accounts != null) {
            log.info("Retrieved " + accounts.size() + " accounts");
        }
        return accounts;
    }
}
