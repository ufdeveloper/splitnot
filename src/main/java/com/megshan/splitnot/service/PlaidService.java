package com.megshan.splitnot.service;

import java.util.List;

/**
 * Created by shantanu on 4/12/17.
 *
 * Plaid Java API helper.
 */
public interface PlaidService {


    List<String> getAllInstitutions();

    String getAccessToken();
}
