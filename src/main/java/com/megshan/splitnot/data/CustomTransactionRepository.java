package com.megshan.splitnot.data;

import com.megshan.splitnot.domain.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Custom repository as described in https://github.com/derjust/spring-data-dynamodb/wiki/Custom-repository-implementations
 */

@Repository
public interface CustomTransactionRepository {
    List<Transaction> findByUserId(String userId);
    int countByUserId(String userId);
}
