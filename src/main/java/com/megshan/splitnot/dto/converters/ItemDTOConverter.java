package com.megshan.splitnot.dto.converters;

import com.megshan.splitnot.domain.Item;
import com.megshan.splitnot.domain.User;
import com.megshan.splitnot.dto.ItemDTO;
import com.megshan.splitnot.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shantanu on 6/28/18.
 */
public class ItemDTOConverter {

    // TODO - add validation
    public static Item convertToItem(ItemDTO itemDTO) {

        Item item = new Item();
        item.setUserKey(itemDTO.getUserKey());
        item.setItemId(itemDTO.getItemId());
        item.setItemName(itemDTO.getItemName());

        return item;
    }

    // TODO - add validation
    public static ItemDTO convertToItemDTO(Item item) {

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setUserKey(item.getUserKey());
        itemDTO.setItemId(item.getItemId());
        itemDTO.setItemName(item.getItemName());

        return itemDTO;
    }

    // TODO - add validation
    public static List<ItemDTO> convertToItemDTOList(List<Item> items) {

        List<ItemDTO> itemDTOList = new ArrayList<>();

        for(Item item : items) {
            itemDTOList.add(convertToItemDTO(item));
        }

        return itemDTOList;
    }
}
