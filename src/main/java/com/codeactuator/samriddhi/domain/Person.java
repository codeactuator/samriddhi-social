/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeactuator.samriddhi.domain;

import com.codeactuator.samriddhi.App;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Ramesh
 */
@Entity
@XmlRootElement
public class Person implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3891599246182243814L;
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private Relation relation;
    private Map<String, Person> relatives;

    public Person(String name, int sex){
        this.name = name;
        this.relatives = new HashMap<>();
        this.relation = new Relation(0, 0, sex, true, false, true, "ME");
    }

    public void addRelative(String name, Relation relationWithPerson){
        Person relative = new Person(name, relation.sex);
        relative.setRelation(relationWithPerson);
        relatives.put(name, relative);
    }

    public Map<String, Person> getRelatives(){
        return this.relatives;
    }

    public Person getRelativeByName(String name){
        return this.relatives.get(name);
    }


    public String getName(){
        return this.name;
    }

    public void setRelation(Relation relation){
        this.relation = relation;
    }

    public Relation getRelation(){
        return this.relation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(name, person.name) &&
                Objects.equals(relation, person.relation);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, relation);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", relation=" + relation +
                ", relatives=" + relatives +
                '}';
    }
}
