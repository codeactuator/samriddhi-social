package com.codeactuator.samriddhi.services.impl;

import com.codeactuator.samriddhi.dao.PersonRepository;
import com.codeactuator.samriddhi.dao.RelationRepository;
import com.codeactuator.samriddhi.domain.Person;
import com.codeactuator.samriddhi.domain.Relation;
import com.codeactuator.samriddhi.domain.Relative;
import com.codeactuator.samriddhi.services.RelationService;
import com.codeactuator.samriddhi.util.RelationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RelationRepository relationRepository;



    public boolean isInLaw(Relative relativeOne, Relative relativeSomeOne, Relative relativeLinked) {
        Relation personOneRelation = relativeOne.getRelation();
        Relation personSomeoneRelation = relativeSomeOne.getRelation();

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


    public boolean isSibling(Relative relativeOne, Relative relativeSomeOne, Relative relativeLinked) {
        Relation personOneRelation = relativeOne.getRelation();
        Relation personSomeoneRelation = relativeSomeOne.getRelation();

        if (personOneRelation.isSibling() && personSomeoneRelation.isSibling()) {
            return true;
        } else {

            return false;
        }
    }


    @Override
    public Relation find(Relative relativeOne, Relative relativeSomeOne, Relative relativeLinked) {
        //Sequence 1.
        // If User trying to see his own relationship
        // with relatives he/she has added the people as relatives.
        // In this case we don't required to do any calculation for making relations.
        if(relativeOne.getPerson().getId() == relativeLinked.getPerson().getId()){
            return relativeSomeOne.getRelation();
        }


        //Sequence 2.
        //If PersonOne trying to find relation with some other person and that other person is
        //already in PersonOne relatives list then we will get the relationship from his list.
        if(relativeOne != null && isExistingRelative(relativeSomeOne.getPerson().getId(), relativeOne.getPerson())){
            return findRelativeByPersonId(relativeSomeOne.getPerson().getId(), relativeOne.getPerson()).get().getRelation();
        } else {
            //More Sequence inside this method.
            return calculate(relativeOne, relativeSomeOne, relativeLinked);
        }
    }

    private Relation calculate(Relative relativeOne, Relative relativeSomeOne, Relative relativeLinked) {
        int x = 0;
        int y = 0;


        //Finding and making positions for the new relationship
        x = relativeOne.getRelation().getX() + relativeSomeOne.getRelation().getX();
        y = relativeOne.getRelation().getY() + relativeSomeOne.getRelation().getY();

        //Sequence 3.
        // If PersonOne is a sibling of LinkedPerson then the
        // Parent Relationship (Position) with the PersonOne would be same as between LinkedPerson.
        if (relativeOne.getRelation().isSibling() && relativeSomeOne.getRelation().getY() > 0) {
            x = relativeLinked.getRelation().getX();
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


        boolean inLaw = isInLaw(relativeOne, relativeSomeOne, relativeLinked);


        //If PersonOne has sibling relationship with LinkedPerson then the sibling relationship with
        //SomeonePerson would be same as with LinkedPerson.
        boolean isSibling = isSibling(relativeOne, relativeSomeOne, relativeLinked);

        Relation result = new Relation(x, y, relativeSomeOne.getRelation().getSex(),
                relativeSomeOne.getRelation().isOwner(), inLaw, isSibling);

        String newRelationName = RelationUtil.getRelations().get(result);
        result.setName(newRelationName);

        //If the resulting relation is not making any sense then
        // change its ownership (switch the Husband/Wife property) and then try to match
        if (!RelationUtil.getRelations().containsKey(result)) {
            result.setOwner(!relativeSomeOne.getRelation().isOwner());
        }

        System.out.println(relativeOne.getPerson().getName() + "\t" + relativeOne.getRelation() + "\t" + RelationUtil.getRelations().get(relativeOne.getRelation()));
        System.out.println(relativeSomeOne.getPerson().getName() + "\t" + relativeSomeOne.getRelation() + "\t" + RelationUtil.getRelations().get(relativeSomeOne.getRelation()));
        System.out.println("RESULT" + "\t" + result + "\t" + RelationUtil.getRelations().get(result));
        System.out.println("=============================================");

        return result;
    }



    public Optional<Relative> findRelativeByPersonId(Long personId, Person linkedPerson){
        for (Relative relative: linkedPerson.getRelatives()){
            if(relative.getPerson().getId() == personId){
                return Optional.of(relative);
            }
        }
        return Optional.empty();
    }

    public boolean isExistingRelative(Long personId, Person person){
        for (Relative relative: person.getRelatives()){
            if(relative.getPerson().getId() == personId){
                return true;
            }
        }
        return false;
    }
}
