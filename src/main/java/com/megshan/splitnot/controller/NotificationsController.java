package com.megshan.splitnot.controller;

import com.megshan.splitnot.service.AuthorizationService;
import com.megshan.splitnot.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationsController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Polls for new transactions received from Plaid.
     * @param jwt
     * @return
     */
    @RequestMapping(path = "/transactions/poll", method = RequestMethod.GET)
    public int pollForTransactions(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getClaimAsString(ClaimTypes.uid.name());
        return transactionService.pollNewTransactionsForUser(userId);
    }
}
