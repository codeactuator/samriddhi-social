package com.codeactuator.samriddhi.services;

import com.codeactuator.samriddhi.dto.PersonDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PersonService {

    public Optional<PersonDTO> save(PersonDTO personDTO);
    public Optional<List<PersonDTO>> findAll();
    public Optional<PersonDTO> findById(Long id);
}
