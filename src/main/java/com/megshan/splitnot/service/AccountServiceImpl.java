package com.megshan.splitnot.service;

import com.megshan.splitnot.domain.Account;
import org.springframework.stereotype.Service;

/**
 * Created by shantanu on 4/12/17.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public Account getAccountForAccountId(String accountId) {
        return new Account(accountId, "somename");
    }
}
