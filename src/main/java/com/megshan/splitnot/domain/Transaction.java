package com.megshan.splitnot.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * Created by shantanu on 12/29/20.
 */

@Data
@ToString
@NoArgsConstructor
@DynamoDBTable(tableName = "splitnot-transaction")
public class Transaction {

    @Id
    @DynamoDBHashKey
    private String id;

    private String name;

    private String amount;

    private String date;

    @DynamoDBIndexHashKey(attributeName = "userId", globalSecondaryIndexName = "userIdAccountIdGSI")
    private String userId;

    @DynamoDBIndexRangeKey(attributeName = "accountId", globalSecondaryIndexName = "userIdAccountIdGSI")
    private String accountId;

    private String accountName;

    public Transaction(String id, String name, String amount, String date, String userId, String accountId, String accountName) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.userId = userId;
        this.accountId = accountId;
        this.accountName = accountName;
    }
}
