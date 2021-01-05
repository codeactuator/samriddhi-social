package com.codeactuator.samriddhi.dto;

import com.codeactuator.samriddhi.domain.Person;
import com.codeactuator.samriddhi.domain.Relation;
import com.codeactuator.samriddhi.domain.Relative;

import java.util.Objects;

public class RelativeDTO implements Marshallable<Relative, RelativeDTO> {


    private Long id;
    private PersonDTO personDTO;
    private RelationDTO relationDTO;


    @Override
    public Relative marshall() {
        Relative relative = new Relative();
        Person person = null;
        Relation relation = null;

        if(personDTO != null) {
            person = personDTO.marshall();
        }
        if(relationDTO != null){
            relation = relationDTO.marshall();
        }

        relative.setId(this.getId());
        relative.setPerson(person);

        return relative;
    }

    @Override
    public void unmarshall(Relative relative) {
        this.setId(relative.getId());
        if(relative.getPerson() != null){
            personDTO = new PersonDTO();
            personDTO.unmarshall(relative.getPerson());
        }
        if(relative.getRelation() != null){
            relationDTO = new RelationDTO();
            relationDTO.unmarshall(relative.getRelation());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public PersonDTO getPersonDTO() {
        return personDTO;
    }

    public void setPersonDTO(PersonDTO personDTO) {
        this.personDTO = personDTO;
    }

    public RelationDTO getRelationDTO() {
        return relationDTO;
    }

    public void setRelationDTO(RelationDTO relationDTO) {
        this.relationDTO = relationDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelativeDTO that = (RelativeDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "RelativeDTO{" +
                "id=" + id +
                ", personDTO=" + personDTO +
                ", relationDTO=" + relationDTO +
                '}';
    }
}
