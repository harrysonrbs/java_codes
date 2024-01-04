package com.projeto.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.spring.entity.Person;
import com.projeto.spring.repository.IRepository;

@RestController
@RequestMapping(value = "/api/people")
public class DataPerson implements ApplicationListener<ContextRefreshedEvent>{

	// injeção de dependência
	IRepository dataSource;
	public DataPerson(IRepository dataSource) {
		this.dataSource = dataSource;
	}
	
	// método para uso do thymeleaf; dependência foi adicionada, o arquivo html foi adicionado, porém não funciona
	@GetMapping(value = "/users")
	public String getPerson( Model model ) {
		model.addAttribute( "userList", this.dataSource.findAll() );
		System.out.println(model.toString());
		return "users";
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Person> getAllPeople() {
		return dataSource.findAll();
	}
	
	@GetMapping(value = "/{idPerson}")
	@ResponseStatus(code = HttpStatus.OK)
	public Optional<Person> getOnePerson(@PathVariable("idPerson") Long id) {
		return dataSource.findById(id);
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
	}
	
}
