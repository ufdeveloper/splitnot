package com.megshan.splitnot.data;

import com.megshan.splitnot.domain.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, String>, CustomTransactionRepository {
}
