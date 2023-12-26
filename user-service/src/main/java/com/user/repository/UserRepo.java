package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, String> {

}
