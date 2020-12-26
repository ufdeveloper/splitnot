package com.megshan.splitnot.service;

import com.megshan.splitnot.domain.Account;
import com.megshan.splitnot.dto.AccountResponse;
import com.megshan.splitnot.dto.AddAccountRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shantanu on 4/12/17.
 */
public interface AccountService {

    // TODO - this will be replaced by accountDao
    List<com.megshan.splitnot.domain.Account> ACCOUNTS_STORE = new ArrayList<>();

    List<AccountResponse> getAccounts();

    AccountResponse addAccount(AddAccountRequest addAccountRequest);
}
