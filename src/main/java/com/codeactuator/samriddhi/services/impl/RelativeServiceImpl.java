package com.codeactuator.samriddhi.services.impl;

import com.codeactuator.samriddhi.dto.RelativeDTO;
import com.codeactuator.samriddhi.services.RelativeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RelativeServiceImpl implements RelativeService {



    @Override
    public Optional<RelativeDTO> save(RelativeDTO relativeDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<List<RelativeDTO>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<RelativeDTO> findById(Long id) {
        return Optional.empty();
    }
}
