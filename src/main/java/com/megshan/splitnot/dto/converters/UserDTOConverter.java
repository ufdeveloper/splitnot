package com.megshan.splitnot.dto.converters;

import com.megshan.splitnot.domain.User;
import com.megshan.splitnot.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shantanu on 6/28/18.
 */
public class UserDTOConverter {

    // TODO - add validation
    public static User convertToUser(UserDTO userDTO) {

        User user = new User();
        user.setFirstName(userDTO.getFisrtName());
        user.setLastName(userDTO.getLastName());

        return user;
    }

    // TODO - add validation
    public static UserDTO convertToUserDTO(User user) {

        UserDTO userDTO = new UserDTO();
        userDTO.setFisrtName(user.getFirstName());
        userDTO.setLastName(user.getLastName());

        return userDTO;
    }

    // TODO - add validation
    public static List<UserDTO> convertToUserDTOList(List<User> users) {

        List<UserDTO> userDTOList = new ArrayList<>();

        for(User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setFisrtName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTOList.add(userDTO);
        }

        return userDTOList;
    }
}
