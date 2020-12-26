package com.megshan.splitnot.service;

import com.megshan.splitnot.domain.Account;
import com.megshan.splitnot.dto.TransactionResponse;
import com.megshan.splitnot.exceptions.NotFoundException;
import com.plaid.client.PlaidClient;
import com.plaid.client.request.ItemGetRequest;
import com.plaid.client.request.TransactionsGetRequest;
import com.plaid.client.request.common.Product;
import com.plaid.client.response.ItemGetResponse;
import com.plaid.client.response.ItemStatus;
import com.plaid.client.response.TransactionsGetResponse;
import com.plaid.client.response.TransactionsGetResponse.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by shantanu on 4/19/17.
 */
@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private PlaidClient plaidClient;

    @Autowired
    private TokenService tokenService;

    @Override
    public List<TransactionResponse> getTransactions(String accountId) throws IOException {

        // get accessToken corresponding to accountId
        Account accountFetched = AccountService.ACCOUNTS_STORE.stream()
                            .filter(account -> account.getId().equals(accountId))   // ids in Plaid are case sensitive
                            .findAny()
                            .orElseThrow(() -> new NotFoundException("account not found with accountId=" + accountId));

        log.info("Found account={} for accountId={}", accountFetched, accountId);

        Response<TransactionsGetResponse> response = plaidClient.service().transactionsGet(
                new TransactionsGetRequest(
                        accountFetched.getPlaidAccessToken(),
                        Date.from(LocalDate.now().minusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())))
                .execute();

        if(!response.isSuccessful()) {
            log.error("Error retrieving transactions"
                    + ", errorMessage=" + response.errorBody().string());
            return null;
        }

        List<Transaction> transactions = response.body().getTransactions();
        log.info("Retrieved " + transactions.size() + " transactions");

        return transactions.stream()
                .map(transaction -> new TransactionResponse(
                        transaction.getTransactionId(),
                        transaction.getName(),
                        String.valueOf(transaction.getAmount()),
                        transaction.getDate()))
                .collect(Collectors.toList());
    }
}
