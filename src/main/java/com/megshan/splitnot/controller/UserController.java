package com.megshan.splitnot.controller;

import com.megshan.splitnot.domain.User;
import com.megshan.splitnot.dto.UserDTO;
import com.megshan.splitnot.dto.UserDTOConverter;
import com.megshan.splitnot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

/**
 * Created by shantanu on 6/28/18.
 *
 * This controller exposes APIs to be getUser(s) and add user.
 */

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    @ResponseStatus(OK)
    public List<UserDTO> getUsers() {
        log.info("getUsers request received");
        List<User> users = userService.getUsers();

        return UserDTOConverter.convertToUserDTOList(users);
    }
}
