package com.megshan.splitnot.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.Data;

import java.io.Serializable;

/**
 * This key class is needed when using spring-data and a hashkey-rangekey as a primary key, since spring-data requires only one field to be annotated with the @Id annotation.
 * https://github.com/derjust/spring-data-dynamodb/wiki/Composite-Primary-Keys-Kotlin-Example
 */

@Data
public class AccountIdKey implements Serializable {
    private static final long serialVersionUID = 1L;

    @DynamoDBHashKey
    private String userId;

    @DynamoDBRangeKey(attributeName = "accountId")
    private String id;

    public AccountIdKey(String userId) {
        this.userId = userId;
    }

    public AccountIdKey(String userId, String id) {
        this.userId = userId;
        this.id = id;
    }
}
