package com.codeactuator.samriddhi.services;

import com.codeactuator.samriddhi.dto.PersonDTO;
import com.codeactuator.samriddhi.dto.RelativeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PersonService {

    //DAO Operations
    public Optional<PersonDTO> save(PersonDTO personDTO);
    public Optional<List<PersonDTO>> saveAll(List<PersonDTO> personDTOS);
    public Optional<List<PersonDTO>> deleteAll();
    public Optional<List<PersonDTO>> findAll();
    public Optional<PersonDTO> findById(Long id);
    public Optional<PersonDTO> findByName(String name);

    //Business Operations
    public Optional<PersonDTO> addRelative(Long personId, RelativeDTO relativeDTO);
}
