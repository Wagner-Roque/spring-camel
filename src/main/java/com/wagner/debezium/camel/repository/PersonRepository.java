package com.wagner.debezium.camel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wagner.debezium.camel.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
