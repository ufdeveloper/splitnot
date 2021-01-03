package com.megshan.splitnot.data;

import com.megshan.splitnot.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Custom repository as described in https://github.com/derjust/spring-data-dynamodb/wiki/Custom-repository-implementations
 */

@Repository
public interface CustomAccountRepository {
    List<Account> findByUserId(String userId);
    Optional<Account> findByItemId(String itemId);
}
