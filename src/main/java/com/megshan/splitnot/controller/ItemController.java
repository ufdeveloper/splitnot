package com.megshan.splitnot.controller;

import com.megshan.splitnot.service.ItemService;
import com.plaid.client.response.ItemStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.http.HttpStatus.OK;

/**
 * Created by shantanu on 4/19/17.
 */
@Slf4j
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping(value= "/items")
    @ResponseStatus(OK)
    public ItemStatus getItem(@RequestParam String userKey) {
        log.info("getItem request received for userKey=" + userKey);
        return itemService.getItemsForUser(userKey);
    }

}
