package com.megshan.splitnot.controller;

import com.megshan.splitnot.service.PlaidService;
import com.megshan.splitnot.service.TokenService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

/**
 * Created by shantanu on 4/13/17.
 */
@RestController
@Slf4j
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/users/{userKey}/accesstokens", params = {"publictoken"}, method = RequestMethod.POST)
    @ResponseStatus(OK)
    public void createAccessToken(@PathVariable("userKey") String userKey,
                                  @RequestParam(value = "publictoken", required = true) String publicToken) throws IOException{
        log.info("addAccessTokenForPublicKey request received");
        tokenService.createAccessToken(userKey, publicToken);
    }
}
