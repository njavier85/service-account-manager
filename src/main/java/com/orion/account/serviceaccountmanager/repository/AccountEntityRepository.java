package com.orion.account.serviceaccountmanager.repository;

import com.orion.account.serviceaccountmanager.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface AccountEntityRepository extends CrudRepository<AccountEntity,Integer> {
}
