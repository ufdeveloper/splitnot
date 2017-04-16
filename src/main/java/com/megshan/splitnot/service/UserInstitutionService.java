package com.megshan.splitnot.service;

import com.megshan.splitnot.domain.UserInstitution;

import com.plaid.client.response.AuthGetResponse;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by shantanu on 4/14/17.
 */
public interface UserInstitutionService {

    AuthGetResponse addInstitutionForUser(String userKey, String accessToken) throws IOException;
    UserInstitution getUserInsititution();
}
