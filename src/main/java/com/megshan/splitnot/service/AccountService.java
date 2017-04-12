package com.megshan.splitnot.service;

import com.megshan.splitnot.domain.Account;

/**
 * Created by shantanu on 4/12/17.
 */
public interface AccountService {

    Account getAccountForAccountId(String accountId);
}
