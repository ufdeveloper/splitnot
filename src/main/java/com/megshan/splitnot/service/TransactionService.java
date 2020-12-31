package com.megshan.splitnot.service;

import com.megshan.splitnot.domain.Transaction;
import com.megshan.splitnot.dto.TransactionResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shantanu on 4/19/17.
 */
public interface TransactionService {

    // TODO - this will be replaced by transactionsDao
    List<Transaction> TRANSACTIONS_STORE = new ArrayList<>();

    List<TransactionResponse> getTransactions(String accountId) throws IOException;

    List<TransactionResponse> getTransactionsByItem(String itemId, int count) throws IOException;

    int pollNewTransactionsForUser(String userId);

    List<TransactionResponse> getNewTransactionsForUser(String userId);

    void addRecentTransactions(List<Transaction> newTransactions);
}
