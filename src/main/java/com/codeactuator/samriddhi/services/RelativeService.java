package com.codeactuator.samriddhi.services;

import com.codeactuator.samriddhi.dto.RelativeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RelativeService {

    public Optional<RelativeDTO> save(RelativeDTO relativeDTO);
    public Optional<List<RelativeDTO>> saveAll(List<RelativeDTO> relativeDTOS);
    public Optional<List<RelativeDTO>> findAll();
    public Optional<RelativeDTO> findById(Long id);
    public Optional<List<RelativeDTO>> deleteAll();
}
