package com.projeto.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projeto.spring.entity.Person;

public interface UserRepository extends JpaRepository<Person, Long> {

	// métodos mágicos
	Person findByName(String name);
	Person findByEmail(String email);
	
	@Query("select u from Person u where u.country like %?1%")
	Person findByCountry(String country);
	
	@Query("select h from Person h where h.city = ?1")
	Person findByCity(String name);
	
}
