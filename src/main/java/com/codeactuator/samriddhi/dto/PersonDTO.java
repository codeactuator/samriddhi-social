package com.codeactuator.samriddhi.dto;

import com.codeactuator.samriddhi.domain.Person;
import com.codeactuator.samriddhi.domain.Relative;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonDTO implements Marshallable<Person, PersonDTO>{

    private long id;
    private String name;
    private List<RelativeDTO> relatives;

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

        List<Relative> relatives = this.relatives.stream()
                .map(relativeDTO -> {
                    return relativeDTO.marshall();
                })
                .collect(Collectors.toList());
        person.setRelatives(relatives);
        return person;
    }

    @Override
    public void unmarshall(Person person) {
        this.setId(person.getId());
        this.setName(person.getName());

        List<RelativeDTO> relativeDTOList = person.getRelatives().stream()
                .map(relative -> {
                    RelativeDTO relativeDTO = new RelativeDTO();
                    relativeDTO.unmarshall(relative);
                    return relativeDTO;
                })
                .collect(Collectors.toList());
        this.setRelatives(relativeDTOList);
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
