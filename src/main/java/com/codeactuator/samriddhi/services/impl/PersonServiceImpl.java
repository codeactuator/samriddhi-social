package com.codeactuator.samriddhi.services.impl;

import com.codeactuator.samriddhi.dto.PersonDTO;
import com.codeactuator.samriddhi.services.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {


    @Override
    public Optional<PersonDTO> save(PersonDTO personDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<List<PersonDTO>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<PersonDTO> findById(Long id) {
        return Optional.empty();
    }
}
