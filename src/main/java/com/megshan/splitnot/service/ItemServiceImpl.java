package com.megshan.splitnot.service;

import com.megshan.splitnot.data.ItemDao;
import com.megshan.splitnot.domain.Item;
import com.plaid.client.PlaidClient;
import com.plaid.client.request.ItemGetRequest;
import com.plaid.client.response.Account;
import com.plaid.client.response.ItemGetResponse;
import com.plaid.client.response.ItemStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by shantanu on 4/19/17.
 */
@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private TokenService tokenService;

//    @Autowired
//    private PlaidClient plaidClient;

//    @Override
//    public ItemStatus getItem(String accessToken) throws IOException{
//        Response<ItemGetResponse> response =
//                plaidClient.service().itemGet(new ItemGetRequest(accessToken)).execute();
//
//        if(!response.isSuccessful()) {
//            log.error("Error retrieving item with accessToken=" + accessToken
//                    + ", errorMessage=" + response.errorBody().string());
//            return null;
//        }
//
//        ItemStatus item = response.body().getItem();
//        log.info("Retrieved itemId=" + item.getItemId()
//                + ", institutionId=" + item.getInstitutionId()
//                + ", availableProducts=" + item.getAvailableProducts()
//                + ", billedProducts=" + item.getBilledProducts()
//                + ", webhook=" + item.getWebhook());
//
//        return item;
//    }

    @Override
    public List<Item> getItemsForUser(Long userKey) {
        List<Item> items = itemDao.getItems(userKey);
        log.info("Successfully retrieved " + items.size() + " items for userKey=" + userKey);
        return items;
    }

    @Override
    public Item getItem(Long userKey, String itemId) {
        Item item = itemDao.getItem(userKey, itemId);
        log.info("Successfully retrieved item=" + item + " for userKey=" + userKey + " and itemId=" + itemId);
        return item;
    }

    @Override
    public void addItem(Item item) {

        // TODO - validate item before saving
        itemDao.addItem(item);
        log.info("Successfully added item=" + item);
    }

    @Override
    public void addItemForPublicToken(Map<String, String> publicTokenRequest) {

        String publicToken = publicTokenRequest.get("publicToken");
        Long userKey = Long.valueOf(publicTokenRequest.get("userKey"));

        // Fetch accessToken and itemId for publicToken
        Map<String, String> tokenExchangeResponse = tokenService.exchangePublicToken(publicToken);

        Item item = new Item();
        item.setItemId(tokenExchangeResponse.get("itemId"));
        item.setAccessToken(tokenExchangeResponse.get("accessToken"));
        item.setUserKey(userKey);

        itemDao.addItem(item);
        log.info("Successfully added item=" + item);
    }
}
