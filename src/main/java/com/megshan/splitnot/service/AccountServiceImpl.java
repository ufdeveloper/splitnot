package com.megshan.splitnot.service;

import com.megshan.splitnot.data.AccountRepository;
import com.megshan.splitnot.domain.Account;
import com.megshan.splitnot.domain.AccountIdKey;
import com.megshan.splitnot.dto.AccountResponse;
import com.megshan.splitnot.dto.AddAccountRequest;
import com.megshan.splitnot.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by shantanu on 4/12/17.
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

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
    public List<AccountResponse> getAccountsByUserId(String userId) {

//        List<AccountIdKey> accountIdKeys = Collections.singletonList(new AccountIdKey(userId));
        return accountRepository.findByUserId(userId).stream()
                .filter(account -> account.getUserId().equals(userId))
                .map(account -> new AccountResponse(account.getId(), account.getName()))
                .collect(Collectors.toList());

//        return ACCOUNTS_STORE
//                .stream()
//                .filter(account -> account.getUserId().equals(userId))
//                .map(account -> new AccountResponse(account.getId(), account.getName()))
//                .collect(Collectors.toList());
    }

    @Override
    public Account getAccountByUserIdAndAccountId(String userId, String accountId) {
//        return ACCOUNTS_STORE
//                .stream()
//                .filter(account -> account.getId().equals(accountId))
//                .findFirst().orElseThrow(() -> new NotFoundException("account not found with id=" + accountId));

        return accountRepository.findById(new AccountIdKey(userId, accountId)).orElseThrow(
                () -> new NotFoundException("account not found with userId=" + userId + " and accountId=" + accountId));
    }

    @Override
    public Optional<Account> getAccountByItemId(String itemId) {
        return accountRepository.findByItemId(itemId);
    }

    @Override
    public AccountResponse addAccount(String userId, AddAccountRequest addAccountRequest) {
        // exchange public_token for access_token
        Map<String, String> exchangePublicTokenResponseMap = tokenService.exchangePublicToken(addAccountRequest.getPublicToken());

        // save <accountId, accountName, itemId, accessToken> mapping
        com.megshan.splitnot.domain.Account account = new com.megshan.splitnot.domain.Account(
                addAccountRequest.getAccountId(),
                addAccountRequest.getAccountName(),
                exchangePublicTokenResponseMap.get("itemId"),
                exchangePublicTokenResponseMap.get("accessToken"),
                userId);

//        ACCOUNTS_STORE.add(account);
        accountRepository.save(account);

        return new AccountResponse(addAccountRequest.getAccountId(), addAccountRequest.getAccountName());
    }
}
