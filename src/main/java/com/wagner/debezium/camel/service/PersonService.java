package com.wagner.debezium.camel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.wagner.debezium.camel.domain.Person;
import com.wagner.debezium.camel.repository.PersonRepository;

public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public List<Person> findAll() {
		
		return personRepository.findAll();
	}

	public Person salvar(Person person) {

		return personRepository.save(person);
	}

	public ResponseEntity<Person> findById(Long id, Person person) {
		var personOp = personRepository.findById(id);
		return personOp.map(p ->{
			p.setName(person.getName());
			p.setAge(person.getAge());
			return ResponseEntity.ok(personRepository.save(p));
		}).orElse(ResponseEntity.noContent().build());
	}

	public void deleteById(Long id) {
		personRepository.deleteById(id);
		
	}
	
}
