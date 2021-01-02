package com.codeactuator.samriddhi.services;

import com.codeactuator.samriddhi.domain.Relation;
import com.codeactuator.samriddhi.domain.Relative;

public interface RelationService {

    public Relation find(Relative relativeOne, Relative relativeSomeOne, Relative relativeLinked);

}
