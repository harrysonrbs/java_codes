package com.projeto.spring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.spring.entity.User;
import com.projeto.spring.repository.UserRepository;

@RestController
@RequestMapping(value = "api/users")
public class DataInit{
		
	private final UserRepository dataSource;
	
	public DataInit(UserRepository dataSource) {
		this.dataSource = dataSource;
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<User> findUsers() {
		return dataSource.findAll();
	}
}
