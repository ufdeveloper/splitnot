package com.megshan.splitnot.dto.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by shantanu on 4/15/17.
 */

@Getter
@Setter
@ToString
public class WebHookResponse {

    // fixed webhook fields
    @JsonProperty("webhook_type")
    String webHookType;

    @JsonProperty("webhook_code")
    String webHookCode;

    @JsonProperty("item_id")
    String itemId;

    @JsonProperty("error")
    WebHookError error;

    // variable webhook fields

    // Fields specific to webhook_type=TRANSACTIONS
    @JsonProperty("new_transactions")
    String newTransactions;

    @JsonProperty("removed_transactions")
    List<String> removedTransactions;

    // Fields specific to webhook_type=ITEM
    @JsonProperty("new_webhook")
    String newWebHookUrl;
}
