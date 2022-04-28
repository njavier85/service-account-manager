package com.orion.account.serviceaccountmanager.repository;

import com.orion.account.serviceaccountmanager.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserEntityRepository extends CrudRepository<UserEntity,Integer> {
}
