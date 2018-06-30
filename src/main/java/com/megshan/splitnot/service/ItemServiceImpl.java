package com.megshan.splitnot.service;

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

/**
 * Created by shantanu on 4/19/17.
 */
@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private PlaidClient plaidClient;

    @Override
    public ItemStatus getItem(String accessToken) throws IOException{
        Response<ItemGetResponse> response =
                plaidClient.service().itemGet(new ItemGetRequest(accessToken)).execute();

        if(!response.isSuccessful()) {
            log.error("Error retrieving item with accessToken=" + accessToken
                    + ", errorMessage=" + response.errorBody().string());
            return null;
        }

        ItemStatus item = response.body().getItem();
        log.info("Retrieved itemId=" + item.getItemId()
                + ", institutionId=" + item.getInstitutionId()
                + ", availableProducts=" + item.getAvailableProducts()
                + ", billedProducts=" + item.getBilledProducts()
                + ", webhook=" + item.getWebhook());

        return item;
    }

    @Override
    public List<ItemStatus> getItemsForUser(String userKey) {



    }

}
