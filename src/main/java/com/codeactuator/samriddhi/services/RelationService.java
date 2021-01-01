package com.codeactuator.samriddhi.services;

import com.codeactuator.samriddhi.domain.Relation;

public interface RelationService {

    public Relation find(Relation personOneRelation, Relation someOneRelation, Relation linkedPersonRelation);
    public Relation find(String personOne, String someOnePerson, String linkedPerson);

}
