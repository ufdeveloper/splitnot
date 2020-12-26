package com.megshan.splitnot.controller;

import com.megshan.splitnot.service.InstitutionService;
import com.plaid.client.response.Institution;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

import java.io.IOException;

/**
 * Created by shantanu on 4/19/17.
 */
@Slf4j
@RestController
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;

//    @GetMapping(value = "/institutions/{institutionId}")
//    @ResponseStatus(OK)
//    public Institution getInstitutionById(@PathVariable("institutionId") String institutionId) throws IOException{
//        log.info("getInstitutionById request received for institutionId=" + institutionId);
//        return institutionService.getInstitutionById(institutionId);
//    }
}
