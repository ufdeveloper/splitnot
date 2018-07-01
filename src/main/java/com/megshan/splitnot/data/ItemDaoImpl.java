package com.megshan.splitnot.data;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.megshan.splitnot.domain.Item;
import com.megshan.splitnot.domain.User;
import com.megshan.splitnot.exceptions.UserDaoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shantanu on 6/30/18.
 */

@Slf4j
@Repository
public class ItemDaoImpl implements ItemDao {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public List<Item> getItems(Long userKey) {

        DynamoDBQueryExpression<Item> dynamoDBQueryExpression
                = new DynamoDBQueryExpression<Item>()
                .withConsistentRead(false)
                .withIndexName("userKey");

        try {
            return dynamoDBMapper.query(Item.class, dynamoDBQueryExpression);
        }
        catch(Exception e) {
            log.error("Failed to load items for userKey=" + userKey + ", exception=" + e);
            throw new UserDaoException( "Failed to load items for userKey=" + userKey);
        }
    }

    @Override
    public void addItem(Item item) {

        try {
            dynamoDBMapper.save(item);
        } catch (Exception e) {
            log.error("Unexpected exception while saving item to DB, item=" + item + ", exception=" + e);
            throw new UserDaoException(e.getMessage());
        }
    }
}
