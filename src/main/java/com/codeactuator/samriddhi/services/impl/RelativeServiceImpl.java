package com.codeactuator.samriddhi.services.impl;

import com.codeactuator.samriddhi.dao.RelativeRepository;
import com.codeactuator.samriddhi.domain.Person;
import com.codeactuator.samriddhi.domain.Relative;
import com.codeactuator.samriddhi.dto.PersonDTO;
import com.codeactuator.samriddhi.dto.RelationDTO;
import com.codeactuator.samriddhi.dto.RelativeDTO;
import com.codeactuator.samriddhi.services.RelativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RelativeServiceImpl implements RelativeService {


    @Autowired
    private RelativeRepository relativeRepository;

    @Override
    public Optional<RelativeDTO> save(RelativeDTO relativeDTO) {
        Relative relative = relativeDTO.marshall();
        relativeRepository.save(relative);
        relativeDTO.unmarshall(relative);
        return Optional.of(relativeDTO);
    }

    @Override
    public Optional<List<RelativeDTO>> saveAll(List<RelativeDTO> relativeDTOS) {
        List<Relative> relativeDTOList = relativeDTOS.stream()
                .map(relativeDTO -> {
                    Relative relative = relativeDTO.marshall();
                    return relative;
                }).collect(Collectors.toList());
        relativeRepository.saveAll(relativeDTOList);
        return Optional.of(relativeDTOS);
    }

    @Override
    public Optional<List<RelativeDTO>> findAll() {
        List<RelativeDTO> relativeDTOS = new ArrayList<>();
        relativeRepository.findAll()
                .forEach(relative -> {
                    RelativeDTO relativeDTO = new RelativeDTO();
                    relativeDTO.unmarshall(relative);
                    relativeDTOS.add(relativeDTO);
                });
        return Optional.of(relativeDTOS);
    }

    @Override
    public Optional<RelativeDTO> findById(Long id) {
        Relative relative = relativeRepository.findById(id).get();
        RelativeDTO relativeDTO = new RelativeDTO();
        relativeDTO.unmarshall(relative);
        return Optional.of(relativeDTO);
    }

    @Override
    public Optional<List<RelativeDTO>> deleteAll() {
        Optional<List<RelativeDTO>> all = findAll();
        relativeRepository.deleteAll();
        return all;
    }


}
