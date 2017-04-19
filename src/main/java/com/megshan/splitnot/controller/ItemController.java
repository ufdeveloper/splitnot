package com.megshan.splitnot.controller;

import com.megshan.splitnot.service.AccountService;
import com.megshan.splitnot.service.ItemService;
import com.plaid.client.response.Account;
import com.plaid.client.response.ItemStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

/**
 * Created by shantanu on 4/19/17.
 */
@Slf4j
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping(value= "/item")
    @ResponseStatus(OK)
    public ItemStatus getItem() throws IOException {
        log.info("getItem request received");
        return itemService.getItem();
    }

}
