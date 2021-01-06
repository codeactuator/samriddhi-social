package com.codeactuator.samriddhi.services.impl;

import com.codeactuator.samriddhi.dao.PersonRepository;
import com.codeactuator.samriddhi.domain.Person;
import com.codeactuator.samriddhi.dto.PersonDTO;
import com.codeactuator.samriddhi.dto.RelationDTO;
import com.codeactuator.samriddhi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
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
    public Optional<List<PersonDTO>> saveAll(List<PersonDTO> personDTOS) {
        List<Person> personList = personDTOS.stream()
                .map(personDTO -> {
                    Person person = personDTO.marshall();
                    return person;
                })
                .collect(Collectors.toList());
        Iterable<Person> persons = personRepository.saveAll(personList);
        return Optional.of(personDTOS);
    }

    @Override
    public Optional<List<PersonDTO>> deleteAll() {
        Optional<List<PersonDTO>> all = findAll();
        personRepository.deleteAll();
        return all;
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

    @Override
    public Optional<PersonDTO> findByName(String name) {
        Person person = personRepository.findByName(name);
        PersonDTO personDTO = new PersonDTO();
        personDTO.unmarshall(person);
        return Optional.of(personDTO);
    }


}
