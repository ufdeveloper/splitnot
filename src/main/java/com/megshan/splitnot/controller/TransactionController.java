package com.megshan.splitnot.controller;

import com.megshan.splitnot.dto.TransactionResponse;
import com.megshan.splitnot.service.TransactionService;
import com.plaid.client.response.TransactionsGetResponse.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/transactions", method = RequestMethod.GET)
    public List<TransactionResponse> getTransactions(@RequestParam String accountId) throws IOException{
        log.info("getTransactions request received for accountId={}", accountId);
        return transactionService.getTransactions(accountId);
    }
}
