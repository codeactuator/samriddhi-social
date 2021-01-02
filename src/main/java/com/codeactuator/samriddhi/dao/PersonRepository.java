package com.codeactuator.samriddhi.dao;

import com.codeactuator.samriddhi.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
