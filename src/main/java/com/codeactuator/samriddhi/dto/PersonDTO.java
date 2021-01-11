package com.codeactuator.samriddhi.dto;

import com.codeactuator.samriddhi.domain.Person;
import com.codeactuator.samriddhi.domain.Relative;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement
public class PersonDTO implements Marshallable<Person, PersonDTO>{

    private long id;
    private String name;
    private List<RelativeDTO> relatives;

    public PersonDTO(){}

    public PersonDTO(String name){
        this.name = name;
    }

    public void addRelative(RelativeDTO relativeDTO){
        if(relatives != null && !relatives.contains(relativeDTO)){
            relatives.add(relativeDTO);
        }else{
            relatives = new ArrayList<>();
            relatives.add(relativeDTO);
        }
    }


    @Override
    public Person marshall() {
        Person person = new Person();
        person.setId(this.getId());
        person.setName(this.getName());

        return person;
    }

    @Override
    public void unmarshall(Person person) {
        this.setId(person.getId());
        this.setName(person.getName());

        if(person.getRelatives() != null) {
            List<RelativeDTO> relativeDTOList = new ArrayList<>();
            person.getRelatives().forEach(relative -> {
                //Don't call relativeDTO.unmarshall() due its bidirectional relation with Person.
                //Person also calls the Person.unmarshall() that will create here a infinite recurssion.
                RelativeDTO relativeDTO = new RelativeDTO();

                RelationDTO relationDTO = new RelationDTO();
                relationDTO.unmarshall(relative.getRelation());

                PersonDTO personDTO = new PersonDTO();
                personDTO.unmarshall(relative.getPerson());

                relativeDTO.setId(relative.getId());
                relativeDTO.setPersonDTO(personDTO);
                relativeDTO.setRelationDTO(relationDTO);
                relativeDTOList.add(relativeDTO);
            });

            this.setRelatives(relativeDTOList);
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

    public List<RelativeDTO> getRelatives() {
        return relatives;
    }

    public void setRelatives(List<RelativeDTO> relatives) {
        this.relatives = relatives;
    }
}
