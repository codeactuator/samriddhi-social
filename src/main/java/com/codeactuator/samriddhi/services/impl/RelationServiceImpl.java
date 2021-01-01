package com.codeactuator.samriddhi.services.impl;

import com.codeactuator.samriddhi.App;
import com.codeactuator.samriddhi.domain.Person;
import com.codeactuator.samriddhi.domain.Relation;
import com.codeactuator.samriddhi.services.RelationService;
import com.codeactuator.samriddhi.util.DataUtil;
import org.springframework.dao.support.DataAccessUtils;

import javax.xml.crypto.Data;
import java.util.List;

public class RelationServiceImpl implements RelationService {


    public int levelOfGeneration(Relation personOneRelation, Relation linkedPersonRelation) {
        return personOneRelation.getY() + linkedPersonRelation.getY();
    }


    public boolean isInLaw(String personOne, String personSomeone, Person linkedPerson) {
        Relation personOneRelation = linkedPerson.getRelativeByName(personOne).getRelation();
        Relation personSomeoneRelation = linkedPerson.getRelativeByName(personSomeone).getRelation();

        //Identify if relation between two people is in-law or not;
        //boolean inLaw = (personOneRelation.isInlaw() && personSomeoneRelation.isInlaw()) ? false : true;


        // If PersonOne is WIFE or HUSBAND and the PersonSomeone Relation with LinkedPerson is not In-Law
        // then the relationship between PersonOne and PersonSomeone is In-Law.
        if ((personOneRelation.getX() == 0 && personOneRelation.getY() == 0) && !personSomeoneRelation.isInlaw()) {
            return true;
        }


        if ((personOneRelation.getX() == 0 && personOneRelation.getY() == 0) && personSomeoneRelation.getY() != 0) {
            return !personSomeoneRelation.isInlaw();
        }

        // If PersonOne and PersonSomeone both has relationship with LinkedPerson as In-Law
        // then the relationship between PersonOne and PersonSomeone is not as In-Law
        if (personOneRelation.isInlaw() && personSomeoneRelation.isInlaw()) {
            return false;
        }


        // If PersonOne and PersonSomeone both has not relationship with LinkedPerson as In-Law
        // then the relationship between PersonOne and PersonSomeone is not as In-Law
        if (!personOneRelation.isInlaw() && !personSomeoneRelation.isInlaw()) {
            return false;
        }


        // If PersonOne or PersonSomeone any one has relationship with LinkedPerson as In-Law
        // then the relationship between PersonOne and PersonSomeone is as In-Law
        if (personOneRelation.isInlaw() || personSomeoneRelation.isInlaw()) {
            return true;
        } else {
            return false;
        }
    }


    public boolean isSibling(String personOne, String personSomeone, Person linkedPerson) {
        Relation personOneRelation = linkedPerson.getRelativeByName(personOne).getRelation();
        Relation personSomeoneRelation = linkedPerson.getRelativeByName(personSomeone).getRelation();
        Relation linkedPersonRelation = linkedPerson.getRelation();

        if (personOneRelation.isSibling() && personSomeoneRelation.isSibling()) {
            return true;
        } else {

            return false;
        }
    }

    @Override
    public Relation find(Relation personOneRelation, Relation someOneRelation, Relation linkedPersonRelation) {
        return null;
    }

    @Override
    public Relation find(String personOneName, String personSomeoneName, String personLinkedName) {

        Person personLinked = DataUtil.getPeople().get(personLinkedName);
        Person personOne = DataUtil.getPeople().get(personOneName);
        Person personSomeOne = DataUtil.getPeople().get(personSomeoneName);

        //Sequence 1.
        // If User trying to see his own relationship
        // with relatives he/she has added the people as relatives.
        // In this case we don't required to do any calculation for making relations.
        if (personOneName.equalsIgnoreCase(personLinkedName)) {
            Relation personSomeoneRelation = personLinked.getRelativeByName(personSomeoneName).getRelation();
            return personSomeoneRelation;
        }


        //Sequence 2.
        //If PersonOne trying to find relation with some other person and that other person is
        //already in PersonOne relatives list then we will get the relationship from his list.
        if (personOne != null && personOne.getRelatives().containsKey(personSomeoneName)) {
            return personOne.getRelatives().get(personSomeoneName).getRelation();
        } else {
            //More Sequence inside this method.
            return calculate(personOneName, personSomeoneName, personLinkedName);
        }
    }

    private Relation calculate(String personOneName, String personSomeoneName, String personLinkedName) {
        Person personLinked = DataUtil.getPeople().get(personLinkedName);
        Relation personOneRelation = personLinked.getRelativeByName(personOneName).getRelation();
        Relation personSomeoneRelation = personLinked.getRelativeByName(personSomeoneName).getRelation();

        int x = 0;
        int y = 0;


        //Finding and making positions for the new relationship
        x = personOneRelation.getX() + personSomeoneRelation.getX();
        y = personOneRelation.getY() + personSomeoneRelation.getY();

        //Sequence 3.
        // If PersonOne is a sibling of LinkedPerson then the
        // Parent Relationship (Position) with the PersonOne would be same as between LinkedPerson.
        if (personOneRelation.isSibling() && personSomeoneRelation.getY() > 0) {
            x = personLinked.getRelation().getX();
        }


        //Any relationship in same generation will fall in Brothers and Sisters as default or In-Law.
        if (x > 1) {
            x = 1; //x = 1 means brother or sister or In-Laws.
        }

        // We will not support relationship above than GREAT GRAND PARENT. Anyone above that will be called as
        // GREAT GRAND PARENT
        // Y = 1 Father
        // Y = 2 Grand Parent
        // Y = 3 Great Grand Parent
        if (y > 3) {
            y = 3;
        }


        boolean inLaw = isInLaw(personOneName, personSomeoneName, personLinked);


        //If PersonOne has sibling relationship with LinkedPerson then the sibling relationship with
        //SomeonePerson would be same as with LinkedPerson.
        boolean isSibling = isSibling(personOneName, personSomeoneName, personLinked);
        Relation result = new Relation(x, y, personSomeoneRelation.getSex(), personSomeoneRelation.isOwner(), inLaw, isSibling);
        String newRelationName = DataUtil.getRelations().get(result);
        result.setName(newRelationName);

        //If the resulting relation is not making any sense then
        // change its ownership (switch the Husband/Wife property) and then try to match
        if (!DataUtil.getRelations().containsKey(result)) {
            result.setOwner(!personSomeoneRelation.isOwner());
        }

        System.out.println(personOneName + "\t" + personOneRelation + "\t" + DataUtil.getRelations().get(personOneRelation));
        System.out.println(personSomeoneName + "\t" + personSomeoneRelation + "\t" + DataUtil.getRelations().get(personSomeoneRelation));
        System.out.println("RESULT" + "\t" + result + "\t" + DataUtil.getRelations().get(result));
        System.out.println("=============================================");

        return result;
    }
}
