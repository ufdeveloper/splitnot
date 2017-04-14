package com.megshan.splitnot.controller;

/**
 * Created by shantanu on 4/11/17.
 */

import com.megshan.splitnot.domain.Account;
import com.megshan.splitnot.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;


/**
 * Controller for managing accounts for users registered with Splitnot.
 *
 */

@Slf4j
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/accounts", params = {"accountid"}, method = RequestMethod.GET)
    @ResponseStatus(OK)
    public Account getAccountByAccountId(@RequestParam(value = "accountid", required = true) String accountId) {
        log.info("getAccountByAccountId request received with id=" + accountId);
        return accountService.getAccountForAccountId(accountId);
    }

}
