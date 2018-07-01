package com.megshan.splitnot.data;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
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

    private static final Integer USERS_SCAN_LIMIT = 5;

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public List<User> getUsers() {

        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression()
                .withConsistentRead(false)
                .withLimit(USERS_SCAN_LIMIT);

        try {
            return dynamoDBMapper.scan(User.class, dynamoDBScanExpression);
        }
        catch( Exception e ) {
            log.error("Failed to scan users from table, exception=" + e);
            throw new UserDaoException( "Failed to scan users from table, exception=" + e);
        }
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

        try {
            dynamoDBMapper.save(user);
        } catch (Exception e) {
            log.error("Unexpected exception while saving user to DB, user=" + user + ", exception=" + e);
            throw new UserDaoException(e.getMessage());
        }
    }
}
