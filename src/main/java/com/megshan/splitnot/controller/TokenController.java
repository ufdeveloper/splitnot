package com.megshan.splitnot.controller;

import com.megshan.splitnot.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.http.HttpStatus.OK;

/**
 * Created by shantanu on 4/13/17.
 */
@RestController
@Slf4j
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/users/{userKey}/accesstokens")
    @ResponseStatus(OK)
    public String createAccessToken(@PathVariable("userKey") String userKey,
                                  @RequestParam(value = "publictoken", required = true) String publicToken) throws IOException{
        log.info("addAccessTokenForPublicKey request received");
        return tokenService.createAccessToken(userKey, publicToken);
    }


    /**
     * Temporary API to test webhook callbacks in conjunction with ngrok.
     * Move this to an integration test later.
     */
    @RequestMapping(value = "/createItem", method = RequestMethod.POST)
    @ResponseStatus(OK)
    public void createItem() throws IOException{
        log.info("createItem request received");
        tokenService.createItem();
    }
}
