package com.megshan.splitnot.service;

import com.megshan.splitnot.domain.Transaction;
import com.megshan.splitnot.dto.TransactionResponse;

import java.io.IOException;
import java.util.List;

/**
 * Created by shantanu on 4/19/17.
 */
public interface TransactionService {

    List<TransactionResponse> getTransactions(String userId, String accountId, Integer count) throws IOException;

    int pollNewTransactionsForUser(String userId);

    List<TransactionResponse> getNewTransactionsForUser(String userId);

    void addRecentTransactions(List<Transaction> newTransactions);
}
