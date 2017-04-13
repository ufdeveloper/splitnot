package com.megshan.splitnot.service;

import com.megshan.splitnot.domain.Account;
import com.plaid.client.response.Institution;

import java.util.List;

/**
 * Created by shantanu on 4/12/17.
 *
 * Plaid Java API helper.
 */
public interface PlaidService {


    List<Institution> getAllInstitutions();
}
