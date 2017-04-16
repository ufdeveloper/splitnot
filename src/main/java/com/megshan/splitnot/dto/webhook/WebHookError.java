package com.megshan.splitnot.dto.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by shantanu on 4/15/17.
 */

@Getter
@Setter
@ToString
public class WebHookError {

    @JsonProperty("error_code")
    String errorCode;

    @JsonProperty("error_type")
    String errorType;

    @JsonProperty("error_message")
    String errorMessage;

    @JsonProperty("status")
    String status;

    @JsonProperty("display_message")
    String displayMessage;
}
