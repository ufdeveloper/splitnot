package com.megshan.splitnot.controller;

import com.megshan.splitnot.service.TransactionService;
import com.plaid.client.response.TransactionsGetResponse.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value = "/transactions", params = {"userKey"})
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getTransactions(Long userKey) throws IOException{
        log.info("getTransactions request received");
        return transactionService.getTransactions(userKey);
    }
}
