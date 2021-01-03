package com.megshan.splitnot.data;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.megshan.splitnot.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class CustomAccountRepositoryImpl implements CustomAccountRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public List<Account> findByUserId(String userId) {
        final Account gsiKeyObj = new Account();
        gsiKeyObj.setUserId(userId);
        final DynamoDBQueryExpression<Account> queryExpression =
                new DynamoDBQueryExpression<>();
        queryExpression.setHashKeyValues(gsiKeyObj);
        final PaginatedQueryList<Account> results =
                dynamoDBMapper.query(Account.class, queryExpression);
        return StreamSupport.stream(results.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<Account> findByItemId(String itemId) {
        final Account gsiKeyObj = new Account();
        gsiKeyObj.setPlaidItemId(itemId);
        final DynamoDBQueryExpression<Account> queryExpression =
                new DynamoDBQueryExpression<>();
        queryExpression.setHashKeyValues(gsiKeyObj);
        queryExpression.setIndexName("itemIdGSI");
        queryExpression.setConsistentRead(false);   // cannot use consistent read on GSI
        final PaginatedQueryList<Account> results =
                dynamoDBMapper.query(Account.class, queryExpression);
        return StreamSupport.stream(results.spliterator(), false).findFirst();
    }
}
