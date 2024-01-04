package com.projeto.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.spring.entity.Person;

@Repository
public interface IRepository extends JpaRepository<Person, Long>{

}
