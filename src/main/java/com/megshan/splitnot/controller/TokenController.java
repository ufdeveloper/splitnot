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

    @PostMapping(value = "/accesstokens")
    @ResponseStatus(OK)
    public String createAccessToken(@RequestParam(value = "publictoken", required = true) String publicToken) throws IOException{
        log.info("createAccessToken request received");
        String accessToken = tokenService.createAccessToken(publicToken);
        tokenService.setAccessToken(accessToken);
        return accessToken;
    }


    /**
     * Temporary API to test webhook callbacks in conjunction with ngrok.
     * Move this to an integration test later.
     */
    @PostMapping(value = "/createItem")
    @ResponseStatus(OK)
    public void createItem() throws IOException{
        log.info("createItem request received");
        tokenService.createItem();
    }
}
