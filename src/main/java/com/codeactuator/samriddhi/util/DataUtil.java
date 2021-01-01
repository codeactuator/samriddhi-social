package com.codeactuator.samriddhi.util;

import com.codeactuator.samriddhi.constants.Relations;
import com.codeactuator.samriddhi.domain.Person;
import com.codeactuator.samriddhi.domain.Relation;

import java.util.HashMap;
import java.util.Map;

public class DataUtil {

    static Map<Relation, String> relations = new HashMap();
    static Map<String, Person> people = new HashMap();

    static {

        //Creating some relationship definition
        Relation me = new Relation(0, 0, 1, true, false, true, Relations.ME.getRelation());

        Relation father = new Relation(0, 1, 1, true, false, true, Relations.FATHER.getRelation());
        Relation mother = new Relation(0, 1, 0, false, false, true, Relations.MOTHER.getRelation());

        Relation fatherInLaw = new Relation(0, 1, 1, true, true, true, Relations.FATHER_IN_LAW.getRelation());
        Relation motherInLaw = new Relation(0, 1, 0, false, true, true, Relations.MOTHER_IN_LAW.getRelation());

        Relation brother = new Relation(1, 0, 1, true, false, true, Relations.BROTHER.getRelation());
        Relation brotherNonSibling = new Relation(1, 0, 1, true, false, false, Relations.BROTHER_NON_SIBLING.getRelation());
        Relation brotherWife = new Relation(1, 0, 0, false, true, false, Relations.BROTHERS_WIFE.getRelation());

        Relation sister = new Relation(1, 0, 0, true, false, true, Relations.SISTER.getRelation());
        Relation sisterNonSibling = new Relation(1, 0, 0, true, false, false, Relations.SISTER_NON_SIBLING.getRelation());
        Relation sistersHusband = new Relation(1, 0, 1, false, true, false, Relations.SISTERS_HUSBAND.getRelation());

        Relation wife = new Relation(0, 0, 0, false, true, true, Relations.WIFE.getRelation());
        Relation husband = new Relation(0, 0, 1, true, true, true, Relations.HUSBAND.getRelation());

        Relation brotherInLaw = new Relation(1, 0, 1, true, true, true, Relations.BROTHER_IN_LAW.getRelation());
        Relation sisterInLaw = new Relation(1, 0, 0, true, true, true, Relations.SISTER_IN_LAW.getRelation());


        Relation son = new Relation(0, -1, 1, true, false, true, Relations.SON.getRelation());
        Relation daughter = new Relation(0, -1, 0, true, false, true, Relations.DOUGHTER.getRelation());




        relations.put(me, me.getName());

        relations.put(father, father.getName());
        relations.put(mother, mother.getName());

        relations.put(fatherInLaw, fatherInLaw.getName());
        relations.put(motherInLaw, motherInLaw.getName());

        relations.put(brother, brother.getName());
        relations.put(brotherNonSibling, brotherNonSibling.getName());
        relations.put(brotherWife, brotherWife.getName());

        relations.put(sister, sister.getName());
        relations.put(sisterNonSibling, sisterNonSibling.getName());
        relations.put(sistersHusband, sistersHusband.getName());

        relations.put(wife, wife.getName());
        relations.put(husband, husband.getName());

        relations.put(brotherInLaw, brotherInLaw.getName());
        relations.put(sisterInLaw, sisterInLaw.getName());


        relations.put(son, son.getName());
        relations.put(daughter, daughter.getName());


        //Initialize some people


        Person shekhar = new Person("SHEKHAR", 1);
        Person ranjeet = new Person("RANJEET", 1);

        shekhar.addRelative("SHEKHAR", me);
        shekhar.addRelative("ANJALI", wife);
        shekhar.addRelative("RAMESH", brother);
        shekhar.addRelative("SHRAVAN", brother);
        shekhar.addRelative("SUJEET", brother);
        shekhar.addRelative("OP GUPTA", father);
        shekhar.addRelative("USHA DEVI", mother);
        shekhar.addRelative("ROHIT", brotherInLaw);
        shekhar.addRelative("NEHA", sisterInLaw);
        shekhar.addRelative("SAMRIDDHI", daughter);
        shekhar.addRelative("AMRITA", sisterNonSibling);
        shekhar.addRelative("SHWETA", sisterNonSibling);
        shekhar.addRelative("CHHOTI", sister);
        shekhar.addRelative("KSHITIJ", brotherInLaw);
        shekhar.addRelative("SANJEET", brotherNonSibling);
        shekhar.addRelative("RANJEET", brother);
        shekhar.addRelative("PRAVEEN KUMAR", fatherInLaw);
        shekhar.addRelative("NILAM DEVI", motherInLaw);




        ranjeet.addRelative("RANJEET", me);
        ranjeet.addRelative("AMRITA", sister);
        ranjeet.addRelative("SHWETA", sister);
        ranjeet.addRelative("AJIT", brother);
        ranjeet.addRelative("KSHITIJ", brotherInLaw);

        people.put(shekhar.getName(), shekhar);
        people.put(ranjeet.getName(), ranjeet);
    }


    public static Map<Relation, String> getRelations() {
        return relations;
    }

    public static Map<String, Person> getPeople() {
        return people;
    }
}
