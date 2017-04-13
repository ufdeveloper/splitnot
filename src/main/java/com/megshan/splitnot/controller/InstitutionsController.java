package com.megshan.splitnot.controller;

import com.megshan.splitnot.domain.Account;
import com.megshan.splitnot.service.AccountService;
import com.megshan.splitnot.service.PlaidService;
import com.plaid.client.response.Institution;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

/**
 * Created by shantanu on 4/13/17.
 */

@Slf4j
@RestController
public class InstitutionsController {

    @Autowired
    private PlaidService plaidService;

    @RequestMapping(value = "/institutions", method = RequestMethod.GET)
    @ResponseStatus(OK)
    public List<Institution> getAllInstitutions() {
        log.info("getAllInstitutions request received");
        return plaidService.getAllInstitutions();
    }
}
