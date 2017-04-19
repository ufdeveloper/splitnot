package com.megshan.splitnot.service;

import com.plaid.client.response.ItemStatus;

import java.io.IOException;

/**
 * Created by shantanu on 4/19/17.
 */
public interface ItemService {

    ItemStatus getItem() throws IOException;
}
