package com.megshan.splitnot.service;

import com.megshan.splitnot.domain.Item;
import com.plaid.client.response.ItemStatus;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by shantanu on 4/19/17.
 */
public interface ItemService {

    List<Item> getItemsForUser(Long userKey);

    Item getItem(Long userKey, String itemId);

    Item addItem(Item item);

    Item addItemForPublicToken(Map<String, String> publicTokenRequest);
}
