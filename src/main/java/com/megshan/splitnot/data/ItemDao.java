package com.megshan.splitnot.data;

import com.megshan.splitnot.domain.Item;

import java.util.List;

/**
 * Created by shantanu on 6/30/18.
 */
public interface ItemDao {

    /**
     * Retrieves all items for the given userKey.
     * @return
     */
    List<Item> getItems(Long userKey);

    /**
     *
     */
    void addItem(Item item);
}
