package com.megshan.splitnot.controller;

/**
 * Created by shantanu on 4/11/17.
 */

import com.megshan.splitnot.dto.AccountResponse;
import com.megshan.splitnot.dto.AddAccountRequest;
import com.megshan.splitnot.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public List<AccountResponse> getAccounts(@AuthenticationPrincipal Jwt jwt) {
        log.info("fetch accounts request received for userId={}", jwt.getClaimAsString(ClaimTypes.uid.name()));
        return accountService.getAccounts(jwt.getClaimAsString(ClaimTypes.uid.name()));
    }

    // TODO - Replace in-memory with DB
    @ResponseStatus(CREATED)
    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public AccountResponse addAccount(@AuthenticationPrincipal Jwt jwt, @RequestBody AddAccountRequest addAccountRequest) {
        log.info("add account request received for userId={}", jwt.getClaimAsString(ClaimTypes.uid.name()));
        return accountService.addAccount(jwt.getClaimAsString(ClaimTypes.uid.name()), addAccountRequest);
    }
}
