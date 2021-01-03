package com.megshan.splitnot.controller;

import com.megshan.splitnot.dto.TransactionResponse;
import com.megshan.splitnot.service.AuthorizationService;
import com.megshan.splitnot.service.ResourceType;
import com.megshan.splitnot.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by shantanu on 4/19/17.
 */

@Slf4j
@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AuthorizationService authorizationService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/transactions", method = RequestMethod.GET)
    public List<TransactionResponse> getTransactions(@RequestParam String accountId, @AuthenticationPrincipal Jwt jwt) throws IOException{
        String userId = jwt.getClaimAsString(ClaimTypes.uid.name());
        authorizationService.authorize(userId, accountId, ResourceType.ACCOUNT);

        log.info("getTransactions request received for accountId={} and userId={}", accountId, userId);
        return transactionService.getTransactions(userId, accountId, null);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/transactions/new", method = RequestMethod.GET)
    public List<TransactionResponse> getNewTransactionsForUser(@AuthenticationPrincipal Jwt jwt) throws IOException{
        String userId = jwt.getClaimAsString(ClaimTypes.uid.name());
        log.info("getNewTransactionsForUser request received for userId={}", userId);
        return transactionService.getNewTransactionsForUser(userId);
    }
}
