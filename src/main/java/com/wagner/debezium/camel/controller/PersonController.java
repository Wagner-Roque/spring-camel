package com.wagner.debezium.camel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wagner.debezium.camel.domain.Person;
import com.wagner.debezium.camel.service.PersonService;

@RestController
@RequestMapping("people")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	public ResponseEntity<?> get(){
		return ResponseEntity.ok(personService.findAll());
	}
	
	@PostMapping
	public ResponseEntity<?>post(@RequestBody Person person ){
		return ResponseEntity.ok(personService.salvar(person));
	}

	@PutMapping("{id}")
	public ResponseEntity<?>put(@PathVariable Long id, @RequestBody Person person){
		return personService.findById(id, person);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		personService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
