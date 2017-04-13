package com.megshan.splitnot.service;

import com.plaid.client.PlaidPublicClient;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by shantanu on 4/12/17.
 */

@Slf4j
@Component
@Getter
@Setter
public class PlaidServiceImpl implements PlaidService {

    @Autowired
    private PlaidPublicClient plaidPublicClient;
    
}
