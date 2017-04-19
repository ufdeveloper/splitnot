package com.megshan.splitnot.service;

import java.io.IOException;

/**
 * Created by shantanu on 4/14/17.
 */
public interface TokenService {

    String createAccessToken(String publicToken) throws IOException;

    void createItem() throws IOException;

    void setAccessToken(String accessToken);

    String getAccessToken();
}
