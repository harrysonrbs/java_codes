package com.projeto.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.spring.entity.Person;

public interface UserRepository extends JpaRepository<Person, Long> {
}
