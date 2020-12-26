package com.megshan.splitnot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    private String id;
    private String name;
    private String plaidItemId;
    private String plaidAccessToken;
}
