package com.megshan.splitnot.service;

import com.megshan.splitnot.domain.User;

import java.util.List;

/**
 * Created by shantanu on 6/28/18.
 *
 * UserService handles user related operations.
 */
public interface UserService {

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
