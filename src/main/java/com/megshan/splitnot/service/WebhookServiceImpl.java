package com.megshan.splitnot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.megshan.splitnot.dto.webhook.WebHookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by shantanu on 4/15/17.
 *
 * Handler for webhook API.
 */

@Slf4j
@Service
public class WebHookServiceImpl {

    public static final String WEBHOOK_URL_PART = "/splitnot/webhook";
    public static final String WEBHOOK_DOMAIN = "http://77ce72fb.ngrok.io";

    private static final String WEBHOOK_TYPE_TRANSACTIONS = "TRANSACTIONS";
    private static final String WEBHOOK_TYPE_ITEM = "ITEM";
    private static final String WEBHOOK_TYPE_INCOME = "INCOME";

    // Fields specific to webhook_type=TRANSACTIONS
    private static final String WEBHOOK_CODE_INITIAL_UPDATE = "INITIAL_UPDATE";
    private static final String WEBHOOK_CODE_HISTORICAL_UPDATE = "HISTORICAL_UPDATE";
    private static final String WEBHOOK_CODE_DEFAULT_UPDATE = "DEFAULT_UPDATE";
    private static final String WEBHOOK_CODE_TRANSACTIONS_REMOVED = "TRANSACTIONS_REMOVED";

    // Fields specific to webhook_type=ITEM
    private static final String WEBHOOK_CODE_WEBHOOK_UPDATE_ACKNOWLEDGED = "WEBHOOK_UPDATE_ACKNOWLEDGED";

    // Fields specific to webhook_type=INCOME
    private static final String WEBHOOK_CODE_PRODUCT_READY = "PRODUCT_READY";

    private static final String WEBHOOK_CODE_ERROR = "ERROR";

    /**
     * This is where webhook call backs are handled.
     *
     * Determine the webhook type and take action accordingly.
     * @param webHookResponse
     */
    public void handleWebHookCall(WebHookResponse webHookResponse) {
        log.info("Received webhook callback from Plaid with webHookResponse=" + webHookResponse);

        if(WEBHOOK_CODE_ERROR.equalsIgnoreCase(webHookResponse.getWebHookCode())) {
            log.error("Error callback received from webhook");
            handleError(webHookResponse);
        }

        // no error in webhook callback, unpack the goodies..

        //TODO - implement logic to unpack the goodies..
        if(WEBHOOK_TYPE_TRANSACTIONS.equalsIgnoreCase(webHookResponse.getWebHookType())) {

            if(WEBHOOK_CODE_INITIAL_UPDATE.equalsIgnoreCase(webHookResponse.getWebHookCode())) {

            }

        }

    }

    protected void handleError(WebHookResponse webHookResponse) {
        //TODO - implement me
    }
}
