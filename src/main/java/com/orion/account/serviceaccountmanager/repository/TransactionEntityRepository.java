package com.orion.account.serviceaccountmanager.repository;

import com.orion.account.serviceaccountmanager.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionEntityRepository extends CrudRepository<TransactionEntity,String> {
}
