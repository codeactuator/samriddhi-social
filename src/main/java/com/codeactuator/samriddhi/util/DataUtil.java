package com.codeactuator.samriddhi.util;

import com.codeactuator.samriddhi.constants.Relations;
import com.codeactuator.samriddhi.domain.Person;
import com.codeactuator.samriddhi.domain.Relation;
import com.codeactuator.samriddhi.domain.Relative;

import java.util.HashMap;
import java.util.Map;

public class DataUtil {

    static Map<Relation, String> relations = new HashMap();
    static Map<Long, Person> people = new HashMap();

    static {

        //Creating some relationship definition
        Relation me = new Relation(0, 0, 1, true, false, true, Relations.ME.getRelation());

        Relation father = new Relation(0, 1, 1, true, false, true, Relations.FATHER.getRelation());
        Relation mother = new Relation(0, 1, 0, false, false, true, Relations.MOTHER.getRelation());

        Relation uncle = new Relation(1, 1, 1, true, false, false, Relations.UNCLE.getRelation());
        Relation aunty = new Relation(1, 1, 0, false, false, false, Relations.AUNTY.getRelation());

        Relation fatherInLaw = new Relation(0, 1, 1, true, true, true, Relations.FATHER_IN_LAW.getRelation());
        Relation motherInLaw = new Relation(0, 1, 0, false, true, true, Relations.MOTHER_IN_LAW.getRelation());

        Relation fatherInLawNonSibling = new Relation(1, 1, 1, true, true, false, Relations.FATHER_IN_LAW_NON_SIBLING.getRelation());
        Relation motherInLawNonSibling = new Relation(1, 1, 0, false, true, false, Relations.MOTHER_IN_LAW_NON_SIBLING.getRelation());

        Relation brother = new Relation(1, 0, 1, true, false, true, Relations.BROTHER.getRelation());
        Relation brotherNonSibling = new Relation(1, 0, 1, true, false, false, Relations.BROTHER_NON_SIBLING.getRelation());
        Relation brotherWife = new Relation(1, 0, 0, false, true, true, Relations.BROTHERS_WIFE.getRelation());
        Relation brotherWifeNonSibling = new Relation(1, 0, 0, false, true, false, Relations.BROTHERS_WIFE_NON_SIBLING.getRelation());

        Relation sister = new Relation(1, 0, 0, true, false, true, Relations.SISTER.getRelation());
        Relation sisterNonSibling = new Relation(1, 0, 0, true, false, false, Relations.SISTER_NON_SIBLING.getRelation());
        Relation sistersHusband = new Relation(1, 0, 1, false, true, true, Relations.SISTERS_HUSBAND.getRelation());
        Relation sistersHusbandNonSibling = new Relation(1, 0, 1, false, true, false, Relations.SISTERS_HUSBAND_NON_SIBLING.getRelation());

        Relation wife = new Relation(0, 0, 0, false, true, true, Relations.WIFE.getRelation());
        Relation husband = new Relation(0, 0, 1, true, true, true, Relations.HUSBAND.getRelation());

        Relation brotherInLaw = new Relation(1, 0, 1, true, true, true, Relations.BROTHER_IN_LAW.getRelation());
        Relation sisterInLaw = new Relation(1, 0, 0, true, true, true, Relations.SISTER_IN_LAW.getRelation());

        Relation brotherInLawNonSibling = new Relation(1, 0, 1, true, true, false, Relations.BROTHER_IN_LAW_NON_SIBLING.getRelation());
        Relation sisterInLawNonSibling = new Relation(1, 0, 0, true, true, false, Relations.SISTER_IN_LAW_NON_SIBLING.getRelation());


        Relation son = new Relation(0, -1, 1, true, false, true, Relations.SON.getRelation());
        Relation daughter = new Relation(0, -1, 0, true, false, true, Relations.DOUGHTER.getRelation());


        relations.put(me, me.getName());

        relations.put(father, father.getName());
        relations.put(mother, mother.getName());

        relations.put(uncle, uncle.getName());
        relations.put(aunty, aunty.getName());

        relations.put(fatherInLaw, fatherInLaw.getName());
        relations.put(motherInLaw, motherInLaw.getName());

        relations.put(fatherInLawNonSibling, fatherInLawNonSibling.getName());
        relations.put(motherInLawNonSibling, motherInLawNonSibling.getName());

        relations.put(brother, brother.getName());
        relations.put(brotherNonSibling, brotherNonSibling.getName());
        relations.put(brotherWife, brotherWife.getName());
        relations.put(brotherWifeNonSibling, brotherWifeNonSibling.getName());

        relations.put(sister, sister.getName());
        relations.put(sisterNonSibling, sisterNonSibling.getName());
        relations.put(sistersHusband, sistersHusband.getName());
        relations.put(sistersHusbandNonSibling, sistersHusbandNonSibling.getName());

        relations.put(wife, wife.getName());
        relations.put(husband, husband.getName());

        relations.put(brotherInLaw, brotherInLaw.getName());
        relations.put(sisterInLaw, sisterInLaw.getName());

        relations.put(brotherInLawNonSibling, brotherInLawNonSibling.getName());
        relations.put(sisterInLawNonSibling, sisterInLawNonSibling.getName());


        relations.put(son, son.getName());
        relations.put(daughter, daughter.getName());


        //Initialize some people


        //Person shekhar = new Person("SHEKHAR", 1);
        //Person ranjeet = new Person("RANJEET", 1);

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
        shekharRelative.setRelation(me);

        Relative anjaliRelative = new Relative();
        anjaliRelative.setId(new Long(2));
        anjaliRelative.setPerson(anjali);
        anjaliRelative.setRelation(wife);

        Relative opGuptaRelative = new Relative();
        opGuptaRelative.setId(new Long(3));
        opGuptaRelative.setPerson(opGupta);
        opGuptaRelative.setRelation(father);

        Relative ushaDeviRelative = new Relative();
        ushaDeviRelative.setId(new Long(4));
        ushaDeviRelative.setPerson(ushaDevi);
        ushaDeviRelative.setRelation(mother);

        Relative rameshRelative = new Relative();
        rameshRelative.setId(new Long(5));
        rameshRelative.setPerson(ramesh);
        rameshRelative.setRelation(brother);

        Relative shravanRelative = new Relative();
        shravanRelative.setId(new Long(6));
        shravanRelative.setPerson(shravan);
        shravanRelative.setRelation(brother);

        Relative sujeetRelative = new Relative();
        sujeetRelative.setId(new Long(7));
        sujeetRelative.setPerson(sujeet);
        sujeetRelative.setRelation(brother);

        Relative sanjeetRelative = new Relative();
        sanjeetRelative.setId(new Long(8));
        sanjeetRelative.setPerson(sanjeet);
        sanjeetRelative.setRelation(brotherNonSibling);

        Relative ranjeetRelative = new Relative();
        ranjeetRelative.setId(new Long(9));
        ranjeetRelative.setPerson(ranjeet);
        ranjeetRelative.setRelation(brother);

        Relative chhotiRelative = new Relative();
        chhotiRelative.setId(new Long(10));
        chhotiRelative.setPerson(chhoti);
        chhotiRelative.setRelation(sister);

        Relative praveenKumarRelative = new Relative();
        praveenKumarRelative.setId(new Long(11));
        praveenKumarRelative.setPerson(praveenKumar);
        praveenKumarRelative.setRelation(fatherInLaw);

        Relative nilamDeviRelative = new Relative();
        nilamDeviRelative.setId(new Long(12));
        nilamDeviRelative.setPerson(nilamDevi);
        nilamDeviRelative.setRelation(motherInLaw);

        Relative rohitRelative = new Relative();
        rohitRelative.setId(new Long(13));
        rohitRelative.setPerson(rohit);
        rohitRelative.setRelation(brotherInLaw);

        Relative nehaRelative = new Relative();
        nehaRelative.setId(new Long(14));
        nehaRelative.setPerson(neha);
        nehaRelative.setRelation(sisterInLaw);

        Relative ashokKumarUncleRelative = new Relative();
        ashokKumarUncleRelative.setId(new Long(15));
        ashokKumarUncleRelative.setPerson(ashokKumar);
        ashokKumarUncleRelative.setRelation(uncle);

        Relative lalmuniDeviAuntyRelative = new Relative();
        lalmuniDeviAuntyRelative.setId(new Long(16));
        lalmuniDeviAuntyRelative.setPerson(lalmuniDevi);
        lalmuniDeviAuntyRelative.setRelation(aunty);

        Relative kishorKumarUncleRelative = new Relative();
        kishorKumarUncleRelative.setId(new Long(17));
        kishorKumarUncleRelative.setPerson(kishorKumar);
        kishorKumarUncleRelative.setRelation(uncle);

        Relative laloDeviAntyRelative = new Relative();
        laloDeviAntyRelative.setId(new Long(18));
        laloDeviAntyRelative.setPerson(laloDevi);
        laloDeviAntyRelative.setRelation(aunty);

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
        amritaRelative.setRelation(sister);

        Relative shwetaRelative = new Relative();
        shwetaRelative.setId(new Long(20));
        shwetaRelative.setPerson(shweta);
        shwetaRelative.setRelation(sister);

        Relative ajitRelative = new Relative();
        ajitRelative.setId(new Long(21));
        ajitRelative.setPerson(ajit);
        ajitRelative.setRelation(brother);

        Relative kshitijRelative = new Relative();
        kshitijRelative.setId(new Long(22));
        kshitijRelative.setPerson(kshitij);
        kshitijRelative.setRelation(sistersHusband);

        Relative ashokKumarFatherRelative = new Relative();
        ashokKumarFatherRelative.setId(new Long(23));
        ashokKumarFatherRelative.setPerson(ashokKumar);
        ashokKumarFatherRelative.setRelation(father);

        Relative lalmuniDeviMotherRelative = new Relative();
        lalmuniDeviMotherRelative.setId(new Long(24));
        lalmuniDeviMotherRelative.setPerson(lalmuniDevi);
        lalmuniDeviMotherRelative.setRelation(mother);

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


    public static Map<Relation, String> getRelations() {
        return relations;
    }

    public static Map<Long, Person> getPeople() {
        return people;
    }
}
