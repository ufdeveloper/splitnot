package com.megshan.splitnot.controller;

/**
 * Created by shantanu on 4/11/17.
 */

import com.megshan.splitnot.service.AccountService;
import com.plaid.client.response.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/accounts")
    @ResponseStatus(OK)
    public List<Account> getAccounts() throws IOException {
        log.info("getAccounts request received");
        return accountService.getAccounts();
    }

}
