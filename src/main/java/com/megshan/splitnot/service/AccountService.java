package com.megshan.splitnot.service;

import com.plaid.client.response.Account;

import java.io.IOException;
import java.util.List;

/**
 * Created by shantanu on 4/12/17.
 */
public interface AccountService {

    List<Account> getAccounts() throws IOException;
}
