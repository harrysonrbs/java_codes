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

import com.projeto.spring.entity.Person;
import com.projeto.spring.repository.UserRepository;

@RestController
@RequestMapping(value = "api/users")
public class DataInit implements ApplicationListener<ContextRefreshedEvent>{
		
	private final UserRepository dataSource;
	
	public DataInit(UserRepository dataSource) {
		this.dataSource = dataSource;
	}
	
	// get listAll
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Person> findAllPeople() {
		return dataSource.findAll();
	}
	
	// get one person
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Optional<Person> getOnePerson(@PathVariable("id") Long identification) {
		return dataSource.findById(identification);
	}
	
	// edit email one person
	@GetMapping("/edit/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void getEditOnePerson(@PathVariable("id") Long identification) {
		Person person = dataSource.getReferenceById(identification);
		person.setName("harryson renan");
		person.setEmail("harrysousa247@gmail.com");
		dataSource.save(person);
	}
	
	
	// delete one person
	@GetMapping("/delete/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deletePerson(@PathVariable("id") Long identification) {
		dataSource.deleteById(identification);
	}
	
	// save new person
	public void newPerson(String name, String email, String city, String country) {
		Person newUsers = new Person(name, email, city, country);
		dataSource.save(newUsers);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if ( findAllPeople().isEmpty() ) {
			newPerson("harryson sousa", "harrysousa245@gmail.com", "osasco", "brazil");
		} else if ( !findAllPeople().isEmpty() ) {
			newPerson("new person", "new.person@gmail.com", "orlando", "usa");
		}
	}
}
