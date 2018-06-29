package com.megshan.splitnot.data;

import com.megshan.splitnot.domain.User;

import java.util.List;

/**
 * Created by shantanu on 6/28/18.
 *
 * Dao Interface for User object.
 */
public interface UserDao {

    /**
     *
     * @return
     */
    List<User> getUsers();

    /**
     *
     * @param userKey
     * @return
     */
    User getUser(Long userKey);

    /**
     *
     * @param user
     */
    void addUser(User user);
}
