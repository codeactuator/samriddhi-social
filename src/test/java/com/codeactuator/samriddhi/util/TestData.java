package com.codeactuator.samriddhi.util;

import com.codeactuator.samriddhi.constants.Relations;
import com.codeactuator.samriddhi.domain.Person;
import com.codeactuator.samriddhi.domain.Relation;
import com.codeactuator.samriddhi.domain.Relative;

import java.util.*;

public class TestData {


    static Map<Long, Person> people = new HashMap();

    static {
        //Initialize some people
        Person shekhar = new Person();
        shekhar.setId(1);
        shekhar.setName("SHEKHAR");

        Person anjali = new Person();
        anjali.setId(2);
        anjali.setName("ANJALI");

        Person opGupta = new Person();
        opGupta.setId(3);
        opGupta.setName("OP GUPTA");

        Person ushaDevi = new Person();
        ushaDevi.setId(4);
        ushaDevi.setName("USHA DEVI");

        Person ramesh = new Person();
        ramesh.setId(5);
        ramesh.setName("RAMESH");

        Person shravan = new Person();
        shravan.setId(6);
        shravan.setName("SHRAVAN");

        Person sujeet = new Person();
        sujeet.setId(7);
        sujeet.setName("SUJEET");

        Person sanjeet = new Person();
        sanjeet.setId(8);
        sanjeet.setName("SANJEET");

        Person ranjeet = new Person();
        ranjeet.setId(9);
        ranjeet.setName("RANJEET");

        Person rohit = new Person();
        rohit.setId(10);
        rohit.setName("ROHIT");

        Person neha = new Person();
        neha.setId(11);
        neha.setName("NEHA");

        Person chhoti = new Person();
        chhoti.setId(12);
        chhoti.setName("CHHOTI");

        Person praveenKumar = new Person();
        praveenKumar.setId(13);
        praveenKumar.setName("PRAVEEN KUMAR");

        Person nilamDevi = new Person();
        nilamDevi.setId(14);
        nilamDevi.setName("NILAM DEVI");

        Person ashokKumar = new Person();
        ashokKumar.setId(15);
        ashokKumar.setName("ASHOK KUMAR");

        Person lalmuniDevi = new Person();
        lalmuniDevi.setId(16);
        lalmuniDevi.setName("LALMUNI DEVI");

        Person kishorKumar = new Person();
        kishorKumar.setId(17);
        kishorKumar.setName("KISHOR KUMAR");

        Person laloDevi = new Person();
        laloDevi.setId(18);
        laloDevi.setName("LALO DEVI");


        Relative shekharRelative = new Relative();
        shekharRelative.setId(new Long(1));
        shekharRelative.setPerson(shekhar);
        shekharRelative.setRelation(RelationUtil.getRelationByName(Relations.ME));

        Relative anjaliRelative = new Relative();
        anjaliRelative.setId(new Long(2));
        anjaliRelative.setPerson(anjali);
        anjaliRelative.setRelation(RelationUtil.getRelationByName(Relations.WIFE));

        Relative opGuptaRelative = new Relative();
        opGuptaRelative.setId(new Long(3));
        opGuptaRelative.setPerson(opGupta);
        opGuptaRelative.setRelation(RelationUtil.getRelationByName(Relations.FATHER));

        Relative ushaDeviRelative = new Relative();
        ushaDeviRelative.setId(new Long(4));
        ushaDeviRelative.setPerson(ushaDevi);
        ushaDeviRelative.setRelation(RelationUtil.getRelationByName(Relations.MOTHER));

        Relative rameshRelative = new Relative();
        rameshRelative.setId(new Long(5));
        rameshRelative.setPerson(ramesh);
        rameshRelative.setRelation(RelationUtil.getRelationByName(Relations.BROTHER));

        Relative shravanRelative = new Relative();
        shravanRelative.setId(new Long(6));
        shravanRelative.setPerson(shravan);
        shravanRelative.setRelation(RelationUtil.getRelationByName(Relations.BROTHER));

        Relative sujeetRelative = new Relative();
        sujeetRelative.setId(new Long(7));
        sujeetRelative.setPerson(sujeet);
        sujeetRelative.setRelation(RelationUtil.getRelationByName(Relations.BROTHER));

        Relative sanjeetRelative = new Relative();
        sanjeetRelative.setId(new Long(8));
        sanjeetRelative.setPerson(sanjeet);
        sanjeetRelative.setRelation(RelationUtil.getRelationByName(Relations.BROTHER_NON_SIBLING));

        Relative ranjeetRelative = new Relative();
        ranjeetRelative.setId(new Long(9));
        ranjeetRelative.setPerson(ranjeet);
        ranjeetRelative.setRelation(RelationUtil.getRelationByName(Relations.BROTHER_NON_SIBLING));

        Relative chhotiRelative = new Relative();
        chhotiRelative.setId(new Long(10));
        chhotiRelative.setPerson(chhoti);
        chhotiRelative.setRelation(RelationUtil.getRelationByName(Relations.SISTER));

        Relative praveenKumarRelative = new Relative();
        praveenKumarRelative.setId(new Long(11));
        praveenKumarRelative.setPerson(praveenKumar);
        praveenKumarRelative.setRelation(RelationUtil.getRelationByName(Relations.FATHER_IN_LAW));

        Relative nilamDeviRelative = new Relative();
        nilamDeviRelative.setId(new Long(12));
        nilamDeviRelative.setPerson(nilamDevi);
        nilamDeviRelative.setRelation(RelationUtil.getRelationByName(Relations.MOTHER_IN_LAW));

        Relative rohitRelative = new Relative();
        rohitRelative.setId(new Long(13));
        rohitRelative.setPerson(rohit);
        rohitRelative.setRelation(RelationUtil.getRelationByName(Relations.BROTHER_IN_LAW));

        Relative nehaRelative = new Relative();
        nehaRelative.setId(new Long(14));
        nehaRelative.setPerson(neha);
        nehaRelative.setRelation(RelationUtil.getRelationByName(Relations.SISTER_IN_LAW));

        Relative ashokKumarUncleRelative = new Relative();
        ashokKumarUncleRelative.setId(new Long(15));
        ashokKumarUncleRelative.setPerson(ashokKumar);
        ashokKumarUncleRelative.setRelation(RelationUtil.getRelationByName(Relations.UNCLE));

        Relative lalmuniDeviAuntyRelative = new Relative();
        lalmuniDeviAuntyRelative.setId(new Long(16));
        lalmuniDeviAuntyRelative.setPerson(lalmuniDevi);
        lalmuniDeviAuntyRelative.setRelation(RelationUtil.getRelationByName(Relations.AUNTY));

        Relative kishorKumarUncleRelative = new Relative();
        kishorKumarUncleRelative.setId(new Long(17));
        kishorKumarUncleRelative.setPerson(kishorKumar);
        kishorKumarUncleRelative.setRelation(RelationUtil.getRelationByName(Relations.UNCLE));

        Relative laloDeviAntyRelative = new Relative();
        laloDeviAntyRelative.setId(new Long(18));
        laloDeviAntyRelative.setPerson(laloDevi);
        laloDeviAntyRelative.setRelation(RelationUtil.getRelationByName(Relations.AUNTY));

        shekhar.addRelative(shekharRelative);
        shekhar.addRelative(anjaliRelative);
        shekhar.addRelative(opGuptaRelative);
        shekhar.addRelative(ushaDeviRelative);
        shekhar.addRelative(rameshRelative);
        shekhar.addRelative(shravanRelative);
        shekhar.addRelative(sujeetRelative);
        shekhar.addRelative(sanjeetRelative);
        shekhar.addRelative(ranjeetRelative);
        shekhar.addRelative(chhotiRelative);
        shekhar.addRelative(praveenKumarRelative);
        shekhar.addRelative(nilamDeviRelative);
        shekhar.addRelative(rohitRelative);
        shekhar.addRelative(nehaRelative);
        shekhar.addRelative(ashokKumarUncleRelative);
        shekhar.addRelative(lalmuniDeviAuntyRelative);
        shekhar.addRelative(kishorKumarUncleRelative);
        shekhar.addRelative(laloDeviAntyRelative);


        Person amrita = new Person();
        amrita.setId(19);
        amrita.setName("AMRITA");

        Person shweta = new Person();
        shweta.setId(20);
        shweta.setName("SHWETA");

        Person ajit = new Person();
        ajit.setId(21);
        ajit.setName("AJIT");

        Person kshitij = new Person();
        kshitij.setId(22);
        kshitij.setName("KSHITIJ");

        Relative amritaRelative = new Relative();
        amritaRelative.setId(new Long(19));
        amritaRelative.setPerson(amrita);
        amritaRelative.setRelation(RelationUtil.getRelationByName(Relations.SISTER));

        Relative shwetaRelative = new Relative();
        shwetaRelative.setId(new Long(20));
        shwetaRelative.setPerson(shweta);
        shwetaRelative.setRelation(RelationUtil.getRelationByName(Relations.SISTER));

        Relative ajitRelative = new Relative();
        ajitRelative.setId(new Long(21));
        ajitRelative.setPerson(ajit);
        ajitRelative.setRelation(RelationUtil.getRelationByName(Relations.BROTHER));

        Relative kshitijRelative = new Relative();
        kshitijRelative.setId(new Long(22));
        kshitijRelative.setPerson(kshitij);
        kshitijRelative.setRelation(RelationUtil.getRelationByName(Relations.SISTERS_HUSBAND));

        Relative ashokKumarFatherRelative = new Relative();
        ashokKumarFatherRelative.setId(new Long(23));
        ashokKumarFatherRelative.setPerson(ashokKumar);
        ashokKumarFatherRelative.setRelation(RelationUtil.getRelationByName(Relations.FATHER));

        Relative lalmuniDeviMotherRelative = new Relative();
        lalmuniDeviMotherRelative.setId(new Long(24));
        lalmuniDeviMotherRelative.setPerson(lalmuniDevi);
        lalmuniDeviMotherRelative.setRelation(RelationUtil.getRelationByName(Relations.MOTHER));

        ranjeet.addRelative(shekharRelative);
        ranjeet.addRelative(ranjeetRelative);
        ranjeet.addRelative(amritaRelative);
        ranjeet.addRelative(shwetaRelative);
        ranjeet.addRelative(ajitRelative);
        ranjeet.addRelative(kshitijRelative);
        ranjeet.addRelative(ashokKumarFatherRelative);
        ranjeet.addRelative(lalmuniDeviMotherRelative);


        people.put(shekhar.getId(), shekhar);
        people.put(ramesh.getId(), ramesh);
        people.put(shravan.getId(), shravan);
        people.put(sujeet.getId(), sujeet);
        people.put(opGupta.getId(), opGupta);
        people.put(ushaDevi.getId(), ushaDevi);
        people.put(anjali.getId(), anjali);
        people.put(chhoti.getId(), chhoti);
        people.put(praveenKumar.getId(), praveenKumar);
        people.put(nilamDevi.getId(), nilamDevi);
        people.put(rohit.getId(), rohit);
        people.put(neha.getId(), neha);
        people.put(ranjeet.getId(), ranjeet);
        people.put(sanjeet.getId(), sanjeet);
        people.put(ajit.getId(), ajit);
        people.put(amrita.getId(), amrita);
        people.put(shweta.getId(), shweta);
        people.put(ashokKumar.getId(), ashokKumar);
        people.put(lalmuniDevi.getId(), lalmuniDevi);
        people.put(kishorKumar.getId(), kishorKumar);
        people.put(laloDevi.getId(), laloDevi);

    }

    public static Map<Long, Person> getPeople() {
        return people;
    }


    public static Person getPersonByName(String personName){
        Collection<Person> persons = people.values();
        for(Person person: persons){
            if(person.getName().equalsIgnoreCase(personName)){
                return person;
            }
        }
        return null;
    }

    public static Relative getRelativeByName(String relativeName, Person person){
        for(Relative relative: person.getRelatives()){
            if(relative.getPerson().getName().equalsIgnoreCase(relativeName)){
                return relative;
            }
        }
        return null;
    }
}