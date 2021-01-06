package com.codeactuator.samriddhi.dao;

import com.codeactuator.samriddhi.domain.Relation;
import org.springframework.data.repository.CrudRepository;

public interface RelationRepository extends CrudRepository<Relation, Long> {

    public Relation findByName(String name);
}
