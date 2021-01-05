package com.codeactuator.samriddhi.services.impl;

import com.codeactuator.samriddhi.dao.PersonRepository;
import com.codeactuator.samriddhi.domain.Person;
import com.codeactuator.samriddhi.dto.PersonDTO;
import com.codeactuator.samriddhi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Optional<PersonDTO> save(PersonDTO personDTO) {
        Person person = personDTO.marshall();
        personRepository.save(person);
        personDTO.unmarshall(person);
        return Optional.of(personDTO);
    }

    @Override
    public Optional<List<PersonDTO>> findAll() {
        List<PersonDTO> personDTOS = new ArrayList<>();
        personRepository.findAll()
                .forEach(person -> {
                    PersonDTO personDTO = new PersonDTO();
                    personDTO.unmarshall(person);
                    personDTOS.add(personDTO);
                });
        return Optional.of(personDTOS);
    }

    @Override
    public Optional<PersonDTO> findById(Long id) {
        Person person = personRepository.findById(id).get();
        PersonDTO personDTO = new PersonDTO();
        personDTO.unmarshall(person);
        return Optional.of(personDTO);
    }
}
