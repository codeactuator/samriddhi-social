package com.codeactuator.samriddhi.services.impl;

import com.codeactuator.samriddhi.constants.Relations;
import com.codeactuator.samriddhi.dao.PersonRepository;
import com.codeactuator.samriddhi.dao.RelationRepository;
import com.codeactuator.samriddhi.dao.RelativeRepository;
import com.codeactuator.samriddhi.domain.Person;
import com.codeactuator.samriddhi.domain.Relation;
import com.codeactuator.samriddhi.domain.Relative;
import com.codeactuator.samriddhi.dto.PersonDTO;
import com.codeactuator.samriddhi.dto.RelationDTO;
import com.codeactuator.samriddhi.dto.RelativeDTO;
import com.codeactuator.samriddhi.services.MasterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class MasterDataServiceImpl implements MasterDataService {

    @Autowired
    private RelationRepository relationRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RelativeRepository relativeRepository;




    @Override
    public List<RelationDTO> createRelations() {
        List<Relation> relations = Arrays.asList(
            new Relation(0, 0, 1, true, false, true, Relations.ME.getRelation()),
            new Relation(0, 1, 1, true, false, true, Relations.FATHER.getRelation()),
            new Relation(0, 1, 0, false, false, true, Relations.MOTHER.getRelation()),

            new Relation(1, 1, 1, true, false, false, Relations.UNCLE.getRelation()),
            new Relation(1, 1, 0, false, false, false, Relations.AUNTY.getRelation()),

            new Relation(0, 1, 1, true, true, true, Relations.FATHER_IN_LAW.getRelation()),
            new Relation(0, 1, 0, false, true, true, Relations.MOTHER_IN_LAW.getRelation()),

            new Relation(1, 1, 1, true, true, false, Relations.FATHER_IN_LAW_NON_SIBLING.getRelation()),
            new Relation(1, 1, 0, false, true, false, Relations.MOTHER_IN_LAW_NON_SIBLING.getRelation()),

            new Relation(1, 0, 1, true, false, true, Relations.BROTHER.getRelation()),
            new Relation(1, 0, 1, true, false, false, Relations.BROTHER_NON_SIBLING.getRelation()),
            new Relation(1, 0, 0, false, true, true, Relations.BROTHERS_WIFE.getRelation()),
            new Relation(1, 0, 0, false, true, false, Relations.BROTHERS_WIFE_NON_SIBLING.getRelation()),

            new Relation(1, 0, 0, true, false, true, Relations.SISTER.getRelation()),
            new Relation(1, 0, 0, true, false, false, Relations.SISTER_NON_SIBLING.getRelation()),
            new Relation(1, 0, 1, false, true, true, Relations.SISTERS_HUSBAND.getRelation()),
            new Relation(1, 0, 1, false, true, false, Relations.SISTERS_HUSBAND_NON_SIBLING.getRelation()),

            new Relation(0, 0, 0, false, true, true, Relations.WIFE.getRelation()),
            new Relation(0, 0, 1, true, true, true, Relations.HUSBAND.getRelation()),

            new Relation(1, 0, 1, true, true, true, Relations.BROTHER_IN_LAW.getRelation()),
            new Relation(1, 0, 0, true, true, true, Relations.SISTER_IN_LAW.getRelation()),

            new Relation(1, 0, 1, true, true, false, Relations.BROTHER_IN_LAW_NON_SIBLING.getRelation()),
            new Relation(1, 0, 0, true, true, false, Relations.SISTER_IN_LAW_NON_SIBLING.getRelation()),

            new Relation(0, -1, 1, true, false, true, Relations.SON.getRelation()),
            new Relation(0, -1, 0, true, false, true, Relations.DOUGHTER.getRelation())
        );


        //clean
        relationRepository.deleteAll();
        Iterable<Relation> relationList = relationRepository.saveAll(relations);
        List<RelationDTO> relationDTOS = new ArrayList<>();
        relationList.forEach(relation -> {
            RelationDTO relationDTO = new RelationDTO();
            relationDTO.unmarshall(relation);
            relationDTOS.add(relationDTO);
        });

        return relationDTOS;
    }

    @Override
    public List<PersonDTO> createPersons() {
        List<Person> persons = Arrays.asList(
            new Person("SHEKHAR"),
            new Person("ANJALI"),
            new Person("OP GUPTA"),
            new Person("USHA DEVI"),
            new Person("RAMESH"),
            new Person("SHRAVAN"),
            new Person("SUJEET"),
            new Person("SANJEET"),
            new Person("RANJEET"),
            new Person("CHHOTI"),
            new Person("AMRITA"),
            new Person("SHWETA"),
            new Person("PRAVEEN KUMAR"),
            new Person("NILAM DEVI"),
            new Person("ROHIT"),
            new Person("NEHA"),
            new Person("ASHOK KUMAR"),
            new Person("LALMUNI DEVI"),
            new Person("KISHOR KUMAR"),
            new Person("LALO DEVI"),
            new Person("AJIT"),
            new Person("KSHITIJ")
        );

        //clean
        personRepository.deleteAll();

        Iterable<Person> personList = personRepository.saveAll(persons);
        List<PersonDTO> personDTOS = new ArrayList<>();
        personList.forEach(person -> {
            PersonDTO personDTO = new PersonDTO();
            personDTO.unmarshall(person);
            personDTOS.add(personDTO);
        });

        return personDTOS;
    }

    @Override
    public List<RelativeDTO> createRelatives() {
        List<RelativeDTO> relativeDTOS = new ArrayList<>();

        Relative shekharRelative = new Relative();
        shekharRelative.setPerson(personRepository.findByName("SHEKHAR"));
        shekharRelative.setRelation(relationRepository.findByName(Relations.ME.getRelation()));
        relativeRepository.save(shekharRelative);

        Relative anjaliRelative = new Relative();
        anjaliRelative.setPerson(personRepository.findByName("ANJALI"));
        anjaliRelative.setRelation(relationRepository.findByName(Relations.WIFE.getRelation()));
        relativeRepository.save(anjaliRelative);

        Relative opGuptaRelative = new Relative();
        opGuptaRelative.setPerson(personRepository.findByName("OP GUPTA"));
        opGuptaRelative.setRelation(relationRepository.findByName(Relations.FATHER.getRelation()));
        relativeRepository.save(opGuptaRelative);


        Relative ushaDeviRelative = new Relative();
        ushaDeviRelative.setPerson(personRepository.findByName("USHA DEVI"));
        ushaDeviRelative.setRelation(relationRepository.findByName(Relations.MOTHER.getRelation()));
        relativeRepository.save(ushaDeviRelative);

        Relative rameshRelative = new Relative();
        rameshRelative.setPerson(personRepository.findByName("RAMESH"));
        rameshRelative.setRelation(relationRepository.findByName(Relations.BROTHER.getRelation()));
        relativeRepository.save(rameshRelative);


        Relative shravanRelative = new Relative();
        shravanRelative.setPerson(personRepository.findByName("SHRAVAN"));
        shravanRelative.setRelation(relationRepository.findByName(Relations.BROTHER.getRelation()));
        relativeRepository.save(shravanRelative);


        Relative sujeetRelative = new Relative();
        sujeetRelative.setPerson(personRepository.findByName("SUJEET"));
        sujeetRelative.setRelation(relationRepository.findByName(Relations.BROTHER.getRelation()));
        relativeRepository.save(sujeetRelative);


        Relative sanjeetRelative = new Relative();
        sanjeetRelative.setPerson(personRepository.findByName("SANJEET"));
        sanjeetRelative.setRelation(relationRepository.findByName(Relations.BROTHER_NON_SIBLING.getRelation()));
        relativeRepository.save(sanjeetRelative);


        Relative ranjeetRelative = new Relative();
        ranjeetRelative.setPerson(personRepository.findByName("RANJEET"));
        ranjeetRelative.setRelation(relationRepository.findByName(Relations.BROTHER_NON_SIBLING.getRelation()));
        relativeRepository.save(ranjeetRelative);


        Relative chhotiRelative = new Relative();
        chhotiRelative.setPerson(personRepository.findByName("CHHOTI"));
        chhotiRelative.setRelation(relationRepository.findByName(Relations.SISTER.getRelation()));
        relativeRepository.save(chhotiRelative);


        Relative amritaRelative = new Relative();
        amritaRelative.setPerson(personRepository.findByName("AMRITA"));
        amritaRelative.setRelation(relationRepository.findByName(Relations.SISTER_NON_SIBLING.getRelation()));
        relativeRepository.save(amritaRelative);


        Relative shwetaRelative = new Relative();
        shwetaRelative.setPerson(personRepository.findByName("SHWETA"));
        shwetaRelative.setRelation(relationRepository.findByName(Relations.SISTER_NON_SIBLING.getRelation()));
        relativeRepository.save(shwetaRelative);


        Relative praveenKumarRelative = new Relative();
        praveenKumarRelative.setPerson(personRepository.findByName("PRAVEEN KUMAR"));
        praveenKumarRelative.setRelation(relationRepository.findByName(Relations.FATHER_IN_LAW.getRelation()));
        relativeRepository.save(praveenKumarRelative);


        Relative nilamDeviRelative = new Relative();
        nilamDeviRelative.setPerson(personRepository.findByName("NILAM DEVI"));
        nilamDeviRelative.setRelation(relationRepository.findByName(Relations.MOTHER_IN_LAW.getRelation()));
        relativeRepository.save(nilamDeviRelative);


        Relative rohitRelative = new Relative();
        rohitRelative.setPerson(personRepository.findByName("ROHIT"));
        rohitRelative.setRelation(relationRepository.findByName(Relations.BROTHER_IN_LAW.getRelation()));
        relativeRepository.save(rohitRelative);


        Relative nehaRelative = new Relative();
        nehaRelative.setPerson(personRepository.findByName("NEHA"));
        nehaRelative.setRelation(relationRepository.findByName(Relations.SISTER_IN_LAW.getRelation()));
        relativeRepository.save(nehaRelative);


        Relative ashokKumarRelative = new Relative();
        ashokKumarRelative.setPerson(personRepository.findByName("ASHOK KUMAR"));
        ashokKumarRelative.setRelation(relationRepository.findByName(Relations.FATHER.getRelation()));
        relativeRepository.save(ashokKumarRelative);


        Relative lalmuniDeviRelative = new Relative();
        lalmuniDeviRelative.setPerson(personRepository.findByName("LALMUNI DEVI"));
        lalmuniDeviRelative.setRelation(relationRepository.findByName(Relations.MOTHER.getRelation()));
        relativeRepository.save(lalmuniDeviRelative);


        Relative kishorKumarRelative = new Relative();
        kishorKumarRelative.setPerson(personRepository.findByName("KISHOR KUMAR"));
        kishorKumarRelative.setRelation(relationRepository.findByName(Relations.FATHER.getRelation()));
        relativeRepository.save(kishorKumarRelative);


        Relative laloDeviRelative = new Relative();
        laloDeviRelative.setPerson(personRepository.findByName("LALO DEVI"));
        laloDeviRelative.setRelation(relationRepository.findByName(Relations.MOTHER.getRelation()));
        relativeRepository.save(lalmuniDeviRelative);


        Relative kshitijRelative = new Relative();
        kshitijRelative.setPerson(personRepository.findByName("KSHITIJ"));
        kshitijRelative.setRelation(relationRepository.findByName(Relations.SISTERS_HUSBAND.getRelation()));
        relativeRepository.save(kshitijRelative);


        Relative ajitRelative = new Relative();
        ajitRelative.setPerson(personRepository.findByName("AJIT"));
        ajitRelative.setRelation(relationRepository.findByName(Relations.BROTHER.getRelation()));
        relativeRepository.save(ajitRelative);

        Person shekhar = personRepository.findByName("SHEKHAR");
        if(shekhar.getRelatives() != null){
            shekhar.getRelatives().clear();
        }
        shekhar.addRelative(shekharRelative);
        shekhar.addRelative(anjaliRelative);
        shekhar.addRelative(opGuptaRelative);
        shekhar.addRelative(ushaDeviRelative);
        shekhar.addRelative(rameshRelative);
        shekhar.addRelative(shravanRelative);
        shekhar.addRelative(sujeetRelative);
        shekhar.addRelative(chhotiRelative);
        shekhar.addRelative(praveenKumarRelative);
        shekhar.addRelative(nilamDeviRelative);
        shekhar.addRelative(rohitRelative);
        shekhar.addRelative(nehaRelative);
        shekhar.addRelative(ranjeetRelative);
        personRepository.save(shekhar);

        /*
        Person ranjeet = personRepository.findByName("RANJEET");
        ranjeet.addRelative(ashokKumarRelative);
        ranjeet.addRelative(lalmuniDeviRelative);
        ranjeet.addRelative(amritaRelative);
        ranjeet.addRelative(shwetaRelative);
        ranjeet.addRelative(kshitijRelative);
        ranjeet.addRelative(ajitRelative);
        ranjeet.addRelative(shekharRelative);
        personRepository.save(ranjeet);

        Person sanjeet = personRepository.findByName("SANJEET");
        sanjeet.addRelative(kishorKumarRelative);
        sanjeet.addRelative(laloDeviRelative);
        sanjeet.addRelative(shekharRelative);
        sanjeet.addRelative(ranjeetRelative);
        personRepository.save(sanjeet);
        */

        return relativeDTOS;
    }

    @Override
    public void deleteRelations() {
        relationRepository.deleteAll();
    }

    @Override
    public void deletePersons() {
        personRepository.deleteAll();
    }

    @Override
    public void deleteRelatives() {
        relativeRepository.deleteAll();
    }
}
