package com.megshan.splitnot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String health() {
        return "healthy";
    }
}
