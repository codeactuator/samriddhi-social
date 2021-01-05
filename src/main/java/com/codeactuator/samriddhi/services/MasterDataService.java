package com.codeactuator.samriddhi.services;

import com.codeactuator.samriddhi.domain.Relation;
import com.codeactuator.samriddhi.dto.PersonDTO;
import com.codeactuator.samriddhi.dto.RelationDTO;
import com.codeactuator.samriddhi.dto.RelativeDTO;

import java.util.List;

public interface MasterDataService {

    public List<RelationDTO> createRelations();
    public List<PersonDTO> createPersons();
    public List<RelativeDTO> createRelative();
}
