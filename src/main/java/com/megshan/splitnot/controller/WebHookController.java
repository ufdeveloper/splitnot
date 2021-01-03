package com.megshan.splitnot.controller;

import com.megshan.splitnot.dto.webhook.WebHookResponse;
import com.megshan.splitnot.service.AuthorizationService;
import com.megshan.splitnot.service.ResourceType;
import com.megshan.splitnot.service.WebHookServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * Created by shantanu on 4/15/17.
 *
 * Webhook controller for the webhook endpoint.
 *
 * The webhook mechanism acts like a queue. This API will be called by Plaid when
 * there is an update on any item registered with Splitnot, for example a new transaction
 * is posted for a user. In essence, this acts like a queue implementation
 * where Plaid puts an item on the queue to which Splitnot is listening to.
 *
 * The API should identify the webhook type sent by Plaid.
 * Plaid supports the following webhook types,
 * TRANSACTIONS, ITEM, INCOME
 *
 * To read more about webhooks, and look at what the API response body would look like,
 * have a look at - https://plaid.com/docs/api/#webhooks
 */
@Slf4j
@RestController
public class WebHookController {

    @Autowired
    private WebHookServiceImpl webHookServiceImpl;

    @Autowired
    private AuthorizationService authorizationService;

    @RequestMapping(value = WebHookServiceImpl.WEBHOOK_URL_PART, method = RequestMethod.POST)
    @ResponseStatus(OK)
    public void listenToWebhook(@RequestBody WebHookResponse webHookResponse) throws IOException{
        log.info("Webhook callback received from Plaid");
        webHookServiceImpl.handleWebHookCall(webHookResponse);
    }

    /**
     * This API is used for testing only.
     * This calls the POST https://sandbox.plaid.com/sandbox/item/fire_webhook API to fire a webhook
     * This is not done via the UI because the request contains the accessToken for the item, which we do not want to expose via the UI.
     */
    @RequestMapping(value = "/fireWebhook", method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public void fireWebhook(@AuthenticationPrincipal Jwt jwt, @RequestParam String accountId) throws IOException {
        String userId = jwt.getClaimAsString(ClaimTypes.uid.name());
        authorizationService.authorize(userId, accountId, ResourceType.ACCOUNT);
        webHookServiceImpl.fireWebhook(userId, accountId);
    }
}
