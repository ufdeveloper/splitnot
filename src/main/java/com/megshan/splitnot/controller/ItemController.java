package com.megshan.splitnot.controller;

import com.megshan.splitnot.domain.Item;
import com.megshan.splitnot.dto.ItemDTO;
import com.megshan.splitnot.dto.converters.ItemDTOConverter;
import com.megshan.splitnot.service.ItemService;
import com.plaid.client.response.ItemStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
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
    public @ResponseBody List<ItemDTO> getItemsForUser(@RequestParam Long userKey) {
        log.info("getItemsForUser request received for userKey=" + userKey);
        List<Item> items = itemService.getItemsForUser(userKey);
        return ItemDTOConverter.convertToItemDTOList(items);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void addItem(@RequestBody Item item) {
        log.info("addItem request received with item=" + item);
        itemService.addItem(item);
    }

}
