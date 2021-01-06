/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeactuator.samriddhi.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 
 * @author Ramesh
 */
@Entity
public class Person implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3891599246182243814L;
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @OneToMany
    private List<Relative> relatives;

    public Person(){}

    public Person(String name){
        this.name = name;
    }

    public void addRelative(Relative relative){
        if(relatives != null && !relatives.contains(relative)){
            relatives.add(relative);
        }else{
            relatives = new ArrayList<>();
            relatives.add(relative);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Relative> getRelatives() {
        return relatives;
    }

    public void setRelatives(List<Relative> relatives) {
        this.relatives = relatives;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", relatives=" + relatives +
                '}';
    }
}
