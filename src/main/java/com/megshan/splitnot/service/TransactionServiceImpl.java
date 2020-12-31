package com.megshan.splitnot.service;

import com.megshan.splitnot.domain.Account;
import com.megshan.splitnot.dto.TransactionResponse;
import com.megshan.splitnot.exceptions.NotFoundException;
import com.plaid.client.PlaidClient;
import com.plaid.client.request.TransactionsGetRequest;
import com.plaid.client.response.TransactionsGetResponse;
import com.plaid.client.response.TransactionsGetResponse.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by shantanu on 4/19/17.
 */
@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

//    @PostConstruct
//    public void addDummyNewTransactions() {
//        TRANSACTIONS_STORE.add(new com.megshan.splitnot.domain.Transaction("transaction1", "Papa Johns", "500", "2020-12-24", "00ujbv54zGAnyS3uO4x6"));
//        TRANSACTIONS_STORE.add(new com.megshan.splitnot.domain.Transaction("transaction2", "Alaska Air", "1500", "2020-12-24", "00ujbv54zGAnyS3uO4x6"));
//    }

    @Autowired
    private PlaidClient plaidClient;

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
                        transaction.getDate(),
                        accountFetched.getId(),
                        accountFetched.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionResponse> getTransactionsByItem(String itemId, int count) throws IOException {

        // get accessToken corresponding to item
        Account accountFetched = AccountService.ACCOUNTS_STORE.stream()
                .filter(account -> account.getPlaidItemId().equals(itemId))   // ids in Plaid are case sensitive
                .findAny()
                .orElseThrow(() -> new NotFoundException("account not found with itemId=" + itemId));

        log.info("Found account={} for itemId={}", accountFetched, itemId);

        Response<TransactionsGetResponse> response = plaidClient.service().transactionsGet(
                new TransactionsGetRequest(
                        accountFetched.getPlaidAccessToken(),
                        Date.from(LocalDate.now().minusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .withCount(count))
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
                        transaction.getDate(),
                        accountFetched.getId(),
                        accountFetched.getName()))
                .collect(Collectors.toList());
    }

    // TODO - return only new transactions size
    @Override
    public int pollNewTransactionsForUser(String userId) {
        return TRANSACTIONS_STORE.size();
//        return 5; // For sandbox testing only
    }

    // TODO - return only new transactions
    @Override
    public List<TransactionResponse> getNewTransactionsForUser(String userId) {
        List<TransactionResponse> transactionResponse = TRANSACTIONS_STORE.stream()
                .map(transaction -> new TransactionResponse(transaction.getId(), transaction.getName(), transaction.getAmount(), transaction.getDate(), transaction.getAccountId(), transaction.getAccountName()))
                .collect(Collectors.toList());

        // clear the transactions store for demo
        TRANSACTIONS_STORE.clear();
        return transactionResponse;
    }

    @Override
    public void addRecentTransactions(List<com.megshan.splitnot.domain.Transaction> newTransactions) {
        TRANSACTIONS_STORE.addAll(newTransactions);
    }
}
