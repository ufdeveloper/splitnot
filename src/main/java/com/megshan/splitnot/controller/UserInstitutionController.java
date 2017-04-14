package com.megshan.splitnot.controller;

import com.megshan.splitnot.domain.Account;
import com.megshan.splitnot.service.AccountService;
import com.megshan.splitnot.service.PlaidService;
import com.plaid.client.response.Institution;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

/**
 * Created by shantanu on 4/13/17.
 *
 * Controller for managing accounts for users registered with Splitnot.
 */

@Slf4j
@RestController
public class UserInstitutionController {

    @Autowired
    private PlaidService plaidService;

//    @RequestMapping(value = "/users/{userKey}/institutions", method = RequestMethod.POST)
//    @ResponseStatus(OK)
//    public List<String> addInstitutionForUser(@PathVariable("userKey") String userKey,
//                                              @RequestBody ) {
//
//        log.info("addInstitutionForUser request received");
//        return plaidService.addInstitutionForUser(userKey, );
//    }
//
//    @RequestMapping(value = "/users/{userKey}/institutions/{institutionKey}", method = RequestMethod.GET)
//    @ResponseStatus(OK)
//    public List<String> getUserInstitution(@PathVariable("userKey") String userKey,
//                                         @PathVariable("institutionKey") String institutionKey) {
//
//        log.info("getUserInstitution request received");
//        return plaidService.getUserInsititution();
//    }
}
