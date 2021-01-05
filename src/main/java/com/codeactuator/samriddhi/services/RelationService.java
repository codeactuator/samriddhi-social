package com.codeactuator.samriddhi.services;

import com.codeactuator.samriddhi.dto.RelationDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RelationService {

    public Optional<RelationDTO> save(RelationDTO relationDTO);
    public Optional<List<RelationDTO>> saveAll(List<RelationDTO> relationDTOS);
    public Optional<List<RelationDTO>> findAll();
    public Optional<RelationDTO> findById(Long id);
    public Optional<List<RelationDTO>> deleteAll();
}
