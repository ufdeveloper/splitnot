package com.megshan.splitnot.service;

import com.plaid.client.response.TransactionsGetResponse.Transaction;

import java.io.IOException;
import java.util.List;

/**
 * Created by shantanu on 4/19/17.
 */
public interface TransactionService {

    List<Transaction> getTransactions() throws IOException;
}
