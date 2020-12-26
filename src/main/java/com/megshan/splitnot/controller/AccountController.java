package com.megshan.splitnot.controller;

/**
 * Created by shantanu on 4/11/17.
 */

import com.megshan.splitnot.dto.AccountResponse;
import com.megshan.splitnot.dto.AddAccountRequest;
import com.megshan.splitnot.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

//    @ResponseStatus(OK)
//    @GetMapping(value = "/accounts")
//    public List<Account> getAccounts() throws IOException {
//        log.info("getAccounts request received");
//        return accountService.getAccounts();
//    }

    // TODO - Replace in-memory with DB
    @ResponseStatus(OK)
    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public List<AccountResponse> getAccounts(HttpServletResponse response) throws IOException {
//        List<AccountResponse> configuredAccountResponses = new ArrayList<>();
//        configuredAccountResponses.add(new AccountResponse("account-id1", "Chase Credit Card"));
//        configuredAccountResponses.add(new AccountResponse("account-id2", "Bank Of America Credit Card"));
//        return configuredAccountResponses;

        log.info("fetching accounts");
        return accountService.getAccounts();
    }

    // TODO - Replace in-memory with DB
    @ResponseStatus(CREATED)
    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public AccountResponse addAccount(@RequestBody AddAccountRequest addAccountRequest, HttpServletResponse response) throws IOException {
        return accountService.addAccount(addAccountRequest);
    }
}
