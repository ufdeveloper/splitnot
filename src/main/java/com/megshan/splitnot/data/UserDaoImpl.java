package com.megshan.splitnot.data;

import com.megshan.splitnot.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shantanu on 6/28/18.
 */

@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getUser(Long userKey) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }
}
