package com.megshan.splitnot.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
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
@DynamoDBTable(tableName = "splitnot-account")
public class Account {

    @Id
    @DynamoDBIgnore
    private AccountIdKey accountIdKey;

    @DynamoDBHashKey
    private String userId;

    @DynamoDBRangeKey(attributeName = "accountId")
    private String id;

    private String name;

    @DynamoDBIndexHashKey(attributeName = "itemId", globalSecondaryIndexName = "itemIdGSI")   // for GSI itemIdGSI
    private String plaidItemId;

    private String plaidAccessToken;

    public Account(String id, String name, String plaidItemId, String plaidAccessToken, String userId) {
        this.id = id;
        this.name = name;
        this.plaidItemId = plaidItemId;
        this.plaidAccessToken = plaidAccessToken;
        this.userId = userId;
    }
}
