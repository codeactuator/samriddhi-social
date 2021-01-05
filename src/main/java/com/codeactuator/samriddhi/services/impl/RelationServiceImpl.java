package com.codeactuator.samriddhi.services.impl;

import com.codeactuator.samriddhi.dao.RelationRepository;
import com.codeactuator.samriddhi.domain.Relation;
import com.codeactuator.samriddhi.dto.RelationDTO;
import com.codeactuator.samriddhi.services.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    private RelationRepository relationRepository;

    @Override
    public Optional<RelationDTO> save(RelationDTO relationDTO) {
        Relation relation = relationDTO.marshall();
        relationRepository.save(relation);
        relationDTO.unmarshall(relation);
        return Optional.of(relationDTO);
    }

    @Override
    public Optional<List<RelationDTO>> saveAll(List<RelationDTO> relationDTOS) {
        List<Relation> relationList = relationDTOS.stream()
                .map(relationDTO -> {
                    Relation relation = relationDTO.marshall();
                    return relation;
                })
                .collect(Collectors.toList());
        Iterable<Relation> relations = relationRepository.saveAll(relationList);

        return Optional.of(relationDTOS);
    }


    @Override
    public Optional<List<RelationDTO>> findAll() {
        List<RelationDTO> relationDTOS = new ArrayList<>();
        relationRepository.findAll()
        .forEach(relation -> {
            RelationDTO relationDTO = new RelationDTO();
            relationDTO.unmarshall(relation);
            relationDTOS.add(relationDTO);
        });
        return Optional.of(relationDTOS);
    }

    @Override
    public Optional<RelationDTO> findById(Long id) {
        RelationDTO relationDTO = new RelationDTO();
        relationRepository.findById(id).ifPresent(relation -> {
            relationDTO.unmarshall(relation);
        });
        return Optional.of(relationDTO);
    }
}
