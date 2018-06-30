package com.megshan.splitnot.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

/**
 * Created by shantanu on 6/30/18.
 */

@Data
@DynamoDBTable(tableName = "items")
public class Item {

    Long userKey;

    String itemId;

    String accessToken;

    String institutionId;
}
