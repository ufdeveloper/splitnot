package com.megshan.splitnot.service;

import java.io.IOException;
import java.util.Map;

/**
 * Created by shantanu on 4/14/17.
 */
public interface TokenService {

    Map<String, String> exchangePublicToken(String publicToken);

//    void createItem() throws IOException;

    void setAccessToken(String accessToken);

    String getAccessToken();
}
