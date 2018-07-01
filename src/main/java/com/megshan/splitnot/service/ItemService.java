package com.megshan.splitnot.service;

import com.megshan.splitnot.domain.Item;
import com.plaid.client.response.ItemStatus;

import java.io.IOException;
import java.util.List;

/**
 * Created by shantanu on 4/19/17.
 */
public interface ItemService {

    List<Item> getItemsForUser(Long userKey);

    void addItem(Item item);

    void addItemForPublicToken(String publicToken);
}
