package com.megshan.splitnot.service;

import java.io.IOException;

/**
 * Created by shantanu on 4/14/17.
 */
public interface TokenService {

    void createAccessToken(String userKey, String publicToken) throws IOException;
}
