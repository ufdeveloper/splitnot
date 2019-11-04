package com.megshan.splitnot.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import lombok.Data;
import lombok.ToString;

import static com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType.S;

/**
 * Created by shantanu on 6/30/18.
 */

@Data
@ToString(exclude = "accessToken")
@DynamoDBTable(tableName = "items")
public class Item {

    @DynamoDBHashKey
    @DynamoDBTyped(S)
    private Long userKey;

    @DynamoDBRangeKey
    String itemId;

    @DynamoDBAttribute
    String accessToken;

    @DynamoDBAttribute
    String itemName;
}
