package com.example.user_service.repository;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findAllByUserId(String userId);

    UserEntity findByEmail(String username);
}
