package com.megshan.splitnot.service;

import com.megshan.splitnot.data.TransactionRepository;
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

    @Autowired
    private PlaidClient plaidClient;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<TransactionResponse> getTransactions(String userId, String accountId, Integer count) throws IOException {

        // get accessToken corresponding to accountId
        Account accountFetched = accountService.getAccountByUserIdAndAccountId(userId, accountId);
        if (accountFetched == null) {
            throw new NotFoundException("account not found with accountId=" + accountId);
        }

        log.info("Found account={} for userId={}, accountId={}", accountFetched, userId, accountId);

        TransactionsGetRequest transactionsGetRequest = new TransactionsGetRequest(
                accountFetched.getPlaidAccessToken(),
                Date.from(LocalDate.now().minusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        if (count != null) {
            transactionsGetRequest.withCount(count);
        }

        Response<TransactionsGetResponse> response = plaidClient.service().transactionsGet(transactionsGetRequest)
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
        return transactionRepository.countByUserId(userId);
//        return 5; // For sandbox testing only
    }

    // TODO - return only new transactions
    @Override
    public List<TransactionResponse> getNewTransactionsForUser(String userId) {

        List<com.megshan.splitnot.domain.Transaction> userTransactions = transactionRepository.findByUserId(userId);

        List<TransactionResponse> transactionResponse = userTransactions.stream()
                .map(transaction -> new TransactionResponse(transaction.getId(), transaction.getName(), transaction.getAmount(), transaction.getDate(), transaction.getAccountId(), transaction.getAccountName()))
                .collect(Collectors.toList());

        // remove the user's transactions for demo
        transactionRepository.deleteAll(userTransactions);

        return transactionResponse;
    }

    @Override
    public void addRecentTransactions(List<com.megshan.splitnot.domain.Transaction> newTransactions) {
        transactionRepository.saveAll(newTransactions);
    }
}
