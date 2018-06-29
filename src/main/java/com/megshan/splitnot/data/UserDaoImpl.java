package com.megshan.splitnot.data;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.megshan.splitnot.domain.User;
import com.megshan.splitnot.exceptions.UserDaoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shantanu on 6/28/18.
 */

@Slf4j
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public List<User> getUsers() {

//        DynamoDBQueryExpression<User> expr = new DynamoDBQueryExpression<User>()
//                .withConsistentRead(false)
//                .withIndexName("userKey");
//
//        try {
//            return dynamoDBMapper.query( User.class, expr );
//        }
//        catch( Exception e ) {
//
//            throw new UserDaoException( "Failed to get users for target="
//                    + Registration.registrationTarget( product, resourceType, productReferenceKey ),
//                    e );
//        }

        return null;
    }

    @Override
    public User getUser(Long userKey) {

        try {
            return dynamoDBMapper.load(User.class, userKey);
        }
        catch(Exception e) {
            log.error("Failed to load user for userKey=" + userKey + ", exception=" + e);
            throw new UserDaoException( "Failed to get user for userKey=" + userKey);
        }
    }

    @Override
    public void addUser(User user) {

    }
}
