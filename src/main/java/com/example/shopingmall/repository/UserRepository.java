package com.example.shopingmall.repository;

import com.example.shopingmall.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  User getByUserId(String userId);

  Optional<User> findByEmail(String email);
}
