package com.megshan.splitnot.service;

import com.megshan.splitnot.data.UserDao;
import com.megshan.splitnot.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shantanu on 6/28/18.
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUsers() {

        List<User> users = userDao.getUsers();
        log.info("Successfully retrieved " + users.size() + " users");

        return users;
    }

    @Override
    public User getUser(Long userKey) {

        User user = userDao.getUser(userKey);
        log.info("Successfully retrieved user for userKey=" + userKey + ", user=" + user);

        return user;
    }

    @Override
    public void addUser(User user) {

        userDao.addUser(user);
        log.info("Successfully added user=" + user);
    }
}
