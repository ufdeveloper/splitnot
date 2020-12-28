package com.megshan.splitnot.controller;

import com.megshan.splitnot.service.TokenService;
import com.plaid.client.PlaidClient;
import com.plaid.client.request.LinkTokenCreateRequest;
import com.plaid.client.response.LinkTokenCreateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;

import static org.springframework.http.HttpStatus.OK;

/**
 * Created by shantanu on 4/13/17.
 */
@RestController
@Slf4j
public class PlaidController {

    @Autowired
    private PlaidClient plaidClient;

    @Autowired
    private TokenService tokenService;

//    @PostMapping(value = "/accesstokens")
//    @ResponseStatus(OK)
//    public String createAccessToken(@RequestParam(value = "publictoken", required = true) String publicToken) throws IOException{
//        log.info("createAccessToken request received for publicToken=" + publicToken);
//        String accessToken = tokenService.createAccessToken(publicToken);
//        return accessToken;
//    }

    @RequestMapping(path = "/getLinkToken", method = RequestMethod.POST)
    public LinkTokenCreateResponse getLinkToken() throws IOException {
        // 1. Grab the client_user_id by searching for the current user in your database
//        User userFromDB = db.find(...);
//        String clientUserId = userFromDB.id;
        LinkTokenCreateRequest.User user = new LinkTokenCreateRequest.User("randomUser");
        // 2. Create a link_token for the given user
        Response<LinkTokenCreateResponse> response =
                plaidClient.service()
                        .linkTokenCreate(
                                new LinkTokenCreateRequest(
                                        user,
                                        "Splitnot",
                                        Collections.singletonList("transactions"),
                                        Collections.singletonList("US"),
                                        "en"
                                )
                                        .withWebhook("http://ca9fd9a85de8.ngrok.io/splitnot/webhook")   // sandbox
                                        .withWebhook("http://ec2-54-164-66-75.compute-1.amazonaws.com:8080/splitnot/webhook") // will be replaced by https://api.splitnot.com/splitnot/webhook //TODO - rename API to /webhook instead of /splitnot/webhook
                        ).execute();
        // 3. Send the data to the client
        log.info("link token response={}", response);
        return response.body();
    }

//    /**
//     * Temporary API to test webhook callbacks in conjunction with ngrok.
//     * Move this to an integration test later.
//     */
//    @PostMapping(value = "/createItem")
//    @ResponseStatus(OK)
//    public void createItem() throws IOException{
//        log.info("createItem request received");
//        tokenService.createItem();
//    }
}
