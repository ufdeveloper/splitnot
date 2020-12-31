package com.megshan.splitnot.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Created by shantanu on 12/29/20.
 */

@Data
@ToString
@AllArgsConstructor
@DynamoDBTable(tableName = "transaction")
public class Transaction {
    private String id;
    private String name;
    private String amount;
    private String date;
    private String userId;
    private String accountId;
    private String accountName;
}
