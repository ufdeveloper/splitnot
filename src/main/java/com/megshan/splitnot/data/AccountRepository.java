package com.megshan.splitnot.data;

import com.megshan.splitnot.domain.Account;
import com.megshan.splitnot.domain.AccountIdKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, AccountIdKey>, CustomAccountRepository {
}
