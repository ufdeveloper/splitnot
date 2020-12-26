package com.megshan.splitnot.service;

import com.megshan.splitnot.domain.Account;
import com.megshan.splitnot.dto.AccountResponse;
import com.megshan.splitnot.dto.AddAccountRequest;
import com.plaid.client.PlaidClient;
import com.plaid.client.request.AccountsGetRequest;
import com.plaid.client.response.AccountsGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

//    @Override
//    public List<Account> getAccounts() throws IOException{
//
//        Response<AccountsGetResponse> response = plaidClient.service().accountsGet(
//                new AccountsGetRequest(tokenService.getAccessToken()))
//                .execute();
//
//        if(!response.isSuccessful()) {
//            log.error("Error retrieving accounts with accessToken=" + tokenService.getAccessToken()
//                    + ", errorMessage=" + response.errorBody().string());
//            return null;
//        }
//
//        List<Account> accounts = response.body().getAccounts();
//        if(accounts != null) {
//            log.info("Retrieved " + accounts.size() + " accounts");
//        }
//        return accounts;
//    }

    @Override
    public List<AccountResponse> getAccounts() {
        return ACCOUNTS_STORE
                .stream()
                .map(account -> new AccountResponse(account.getId(), account.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponse addAccount(AddAccountRequest addAccountRequest) {
        // exchange public_token for access_token
        Map<String, String> exchangePublicTokenResponseMap = tokenService.exchangePublicToken(addAccountRequest.getPublicToken());

        // save <accountId, accountName, itemId, accessToken> mapping
        com.megshan.splitnot.domain.Account account = new com.megshan.splitnot.domain.Account(
                addAccountRequest.getAccountId(),
                addAccountRequest.getAccountName(),
                exchangePublicTokenResponseMap.get("itemId"),
                exchangePublicTokenResponseMap.get("accessToken"));
        ACCOUNTS_STORE.add(account);

        return new AccountResponse(addAccountRequest.getAccountId(), addAccountRequest.getAccountName());
    }
}
