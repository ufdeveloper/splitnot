package com.megshan.splitnot.data;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.megshan.splitnot.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class CustomTransactionRepositoryImpl implements CustomTransactionRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public List<Transaction> findByUserId(String userId) {
        final Transaction gsiKeyObj = new Transaction();
        gsiKeyObj.setUserId(userId);
        final DynamoDBQueryExpression<Transaction> queryExpression =
                new DynamoDBQueryExpression<>();
        queryExpression.setHashKeyValues(gsiKeyObj);
        queryExpression.setIndexName("userIdAccountIdGSI");
        queryExpression.setConsistentRead(false);   // cannot use consistent read on GSI
        final PaginatedQueryList<Transaction> results =
                dynamoDBMapper.query(Transaction.class, queryExpression);
        return StreamSupport.stream(results.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public int countByUserId(String userId) {
        final Transaction gsiKeyObj = new Transaction();
        gsiKeyObj.setUserId(userId);
        final DynamoDBQueryExpression<Transaction> queryExpression =
                new DynamoDBQueryExpression<>();
        queryExpression.setHashKeyValues(gsiKeyObj);
        queryExpression.setIndexName("userIdAccountIdGSI");
        queryExpression.setConsistentRead(false);   // cannot use consistent read on GSI
        return dynamoDBMapper.count(Transaction.class, queryExpression);
    }
}
