package com.codeactuator.samriddhi.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Relative implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Person person;
    @ManyToOne
    private Relation relation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relative relative = (Relative) o;
        return Objects.equals(id, relative.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
