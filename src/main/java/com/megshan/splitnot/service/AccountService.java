package com.megshan.splitnot.service;

import com.megshan.splitnot.domain.Account;
import com.megshan.splitnot.dto.AccountResponse;
import com.megshan.splitnot.dto.AddAccountRequest;

import java.util.List;
import java.util.Optional;

/**
 * Created by shantanu on 4/12/17.
 */
public interface AccountService {

    List<AccountResponse> getAccountsByUserId(String userId);

    Account getAccountByUserIdAndAccountId(String userId, String accountId);

    Optional<Account> getAccountByItemId(String userId);

    AccountResponse addAccount(String userId, AddAccountRequest addAccountRequest);
}
