package com.megshan.splitnot.service;

import com.megshan.splitnot.domain.Account;
import com.megshan.splitnot.domain.Transaction;
import com.megshan.splitnot.dto.TransactionResponse;
import com.megshan.splitnot.dto.webhook.WebHookResponse;
import com.megshan.splitnot.exceptions.NotFoundException;
import com.plaid.client.PlaidClient;
import com.plaid.client.request.SandboxItemFireWebhookRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by shantanu on 4/15/17.
 *
 * Handler for webhook API.
 */

@Slf4j
@Service
public class WebHookServiceImpl {

    public static final String WEBHOOK_URL_PART = "/webhook";
    public static final String WEBHOOK_DOMAIN = "http://74a41d6d.ngrok.io";

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

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PlaidClient plaidClient;

    /**
     * This is where webhook call backs are handled.
     *
     * Determine the webhook type and take action accordingly.
     * @param webHookResponse
     */
    public void handleWebHookCall(WebHookResponse webHookResponse) throws IOException {
        log.info("Received webhook callback from Plaid with webHookResponse=" + webHookResponse);

        if(WEBHOOK_CODE_ERROR.equalsIgnoreCase(webHookResponse.getWebHookCode())) {
            log.error("Error callback received from webhook={}", webHookResponse);
            return; // We do not throw an exception here because the consumer of this API is plaid itself, not a user of Splitnot.
        }

        if(!WEBHOOK_TYPE_TRANSACTIONS.equalsIgnoreCase(webHookResponse.getWebHookType()) || !WEBHOOK_CODE_DEFAULT_UPDATE.equalsIgnoreCase(webHookResponse.getWebHookCode())) {
            log.warn("Non-transaction default update webhook call received, webhookDetails={}", webHookResponse);
            return;
        }

        // find number of recent transactions
        int numNewTransactions = Integer.parseInt(webHookResponse.getNumNewTransactions());
        if (numNewTransactions < 1) {
            log.warn("transactions update webhook call received with no new transactions, but using default numNewTransactions=5 for sandbox testing, webhookDetails={}", webHookResponse);
//            return; // TODO - uncomment this after sandbox testing.
            // for sandbox testing, always fetch first 5 transactions
            numNewTransactions = 5;
        }

        String itemId = webHookResponse.getItemId();

        // fetch userId associated with itemId
        Account account = accountService.getAccountByItemId(itemId).orElseThrow(() -> new NotFoundException("account not found with itemId=" + itemId));

        // fetch new transactions for item from Plaid
        List<TransactionResponse> newTransactions = transactionService.getTransactions(account.getUserId(), account.getId(), numNewTransactions);

        // add new transactions to transactions store
        transactionService.addRecentTransactions(newTransactions.stream()
                .map(newTransaction -> new Transaction(newTransaction.getId(), newTransaction.getName(), newTransaction.getAmount(),
                        newTransaction.getDate(), account.getUserId(), newTransaction.getAccountId(), newTransaction.getAccountName()))
                .collect(Collectors.toList()));
    }

    public void fireWebhook(String userId, String accountId) throws IOException {

        // fetch account from accountId
        Account account = accountService.getAccountByUserIdAndAccountId(userId, accountId);

        // fire sandbox webhook
        SandboxItemFireWebhookRequest sandboxItemFireWebhookRequest = new SandboxItemFireWebhookRequest(account.getPlaidAccessToken(), WEBHOOK_CODE_DEFAULT_UPDATE);
        plaidClient.service().sandboxItemFireWebhook(sandboxItemFireWebhookRequest).execute();

        log.info("successfully fired sandbox webhook for userId={}, accountId={}", userId, accountId);
    }
}
