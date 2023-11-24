package com.projeto.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.spring.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
