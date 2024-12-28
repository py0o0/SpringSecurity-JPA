package com.example.apiServer.repository;

import com.example.apiServer.dto.UserDto;
import com.example.apiServer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserId(String username);
}
