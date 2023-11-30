package com.projeto.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.spring.entity.User;
import com.projeto.spring.repository.UserRepository;

@RestController
@RequestMapping(value = "api/users")
public class DataInit implements ApplicationListener<ContextRefreshedEvent>{
		
	private final UserRepository dataSource;
	
	public DataInit(UserRepository dataSource) {
		this.dataSource = dataSource;
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<User> findUsers() {
		return dataSource.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Optional<User> getOneUser(@PathVariable("id") Long identification) {
		return dataSource.findById(identification);
	}
	
	public void newUsers(String name, Integer age, String email) {
		User newUsers = new User(name, age, email);
		dataSource.save(newUsers);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if ( !findUsers().isEmpty() ) {
			newUsers("new user", 100, "new.user@gmail.com");
		}
	}
}
