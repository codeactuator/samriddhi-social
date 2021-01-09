package com.codeactuator.samriddhi.services;

import com.codeactuator.samriddhi.domain.Relation;
import com.codeactuator.samriddhi.domain.Relative;
import com.codeactuator.samriddhi.dto.RelationDTO;
import com.codeactuator.samriddhi.dto.RelativeDTO;

public interface RelativeCalculationService {

    //This is just to test the Junit test cases.
    public Relation find(Relative relativeOne, Relative relativeSomeOne, Relative relativeLinked);

    public RelativeDTO find(Long relativeOneId, Long relativeSomeOneId, Long relativeLinkedId);
    public RelativeDTO find(RelativeDTO relativeOne, RelativeDTO relativeSomeOne, RelativeDTO relativeLinked);

    public RelativeDTO addRelative(Long relativeOneId, Long relativeSomeOneId, Long relativeLinkedId);
    public RelativeDTO addRelative(RelativeDTO relativeOne, RelativeDTO relativeSomeOne, RelativeDTO relativeLinked);
}
