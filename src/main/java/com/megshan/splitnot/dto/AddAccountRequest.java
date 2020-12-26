package com.megshan.splitnot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddAccountRequest {
    private String publicToken;
    private String accountId;
    private String accountName;
}
