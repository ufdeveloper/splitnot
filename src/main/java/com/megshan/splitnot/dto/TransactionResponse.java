package com.megshan.splitnot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionResponse {
    private String id;
    private String name;
    private String amount;
    private String date;
}
