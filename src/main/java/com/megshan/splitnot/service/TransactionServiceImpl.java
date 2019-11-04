package com.megshan.splitnot.service;

import com.megshan.splitnot.data.ItemDao;
import com.megshan.splitnot.domain.Item;
import com.megshan.splitnot.exceptions.InternalServerErrorException;
import com.plaid.client.PlaidClient;
import com.plaid.client.request.TransactionsGetRequest;
import com.plaid.client.response.TransactionsGetResponse;
import com.plaid.client.response.TransactionsGetResponse.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by shantanu on 4/19/17.
 */
@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private PlaidClient plaidClient;

    @Autowired
    private ItemDao itemDao;

    @Override
    public List<Transaction> getTransactions(Long userKey) throws IOException {

        log.info("request received to getTransactions for userKey={}", userKey);

        // get accessToken and itemItem for user
        List<Item> itemList = itemDao.getItems(userKey);
        if(CollectionUtils.isEmpty(itemList)) {
            log.info("no items found for userKey={}", userKey);
            return Collections.EMPTY_LIST;
        }

        Item item = itemList.get(0);
        String accessToken = item.getAccessToken();
        String itemId = item.getItemId();
        log.info("fetching transactions for userKey={} and itemId={}", userKey, itemId);

        Response<TransactionsGetResponse> response = plaidClient.service().transactionsGet(
                new TransactionsGetRequest(
                        accessToken,
                        Date.from(LocalDate.now().minusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())))
                .execute();

        if(!response.isSuccessful()) {
            log.error("Error retrieving transactions for userKey={}"
                    + ", errorMessage={}", userKey, response.errorBody().string());
            throw new InternalServerErrorException("error retrieving transactions");
        }

        List<Transaction> transactions = response.body().getTransactions();
        log.info("Retrieved {} transactions for userKey={} and itemId={}", transactions.size(), userKey, itemId);

        return transactions;
    }
}
