package com.megshan.splitnot.controller;

import com.megshan.splitnot.domain.User;
import com.megshan.splitnot.dto.UserDTO;
import com.megshan.splitnot.dto.converters.UserDTOConverter;
import com.megshan.splitnot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * Created by shantanu on 6/28/18.
 *
 * This controller exposes APIs to be getUser(s) and add user.
 *
 */

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    @ResponseStatus(OK)
    public @ResponseBody List<UserDTO> getUsers() {
        log.info("getUsers request received");
        List<User> users = userService.getUsers();
        return UserDTOConverter.convertToUserDTOList(users);
    }

    @GetMapping(value = "/users/{userKey}")
    @ResponseStatus(OK)
    public @ResponseBody UserDTO getUser(@PathVariable("userKey") Long userKey) {
        log.info("getUser request received for userKey=" + userKey);
        User user = userService.getUser(userKey);
        return UserDTOConverter.convertToUserDTO(user);
    }

    // TODO - add validation for userDTO before saving
    @PostMapping(value= "/users")
    @ResponseStatus(CREATED)
    public void addUser(@RequestBody UserDTO userDTO) {
        log.info("addUser request received with request body, " + userDTO);
        User user = UserDTOConverter.convertToUser(userDTO);
        userService.addUser(user);
    }
}
