package com.codeactuator.samriddhi.services.impl;

import com.codeactuator.samriddhi.domain.Relation;
import com.codeactuator.samriddhi.domain.Relative;
import com.codeactuator.samriddhi.dto.PersonDTO;
import com.codeactuator.samriddhi.dto.RelationDTO;
import com.codeactuator.samriddhi.dto.RelativeDTO;
import com.codeactuator.samriddhi.services.RelativeCalculationService;
import com.codeactuator.samriddhi.services.RelativeService;
import com.codeactuator.samriddhi.util.RelationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RelativeCalculationServiceImpl implements RelativeCalculationService {


    @Autowired
    private RelativeService relativeService;

    /**
     * This method is use to test the Junit cases.
     * @param relativeOne
     * @param relativeSomeOne
     * @param relativeLinked
     * @return
     */
    @Override
    public Relation find(Relative relativeOne, Relative relativeSomeOne, Relative relativeLinked) {

        RelativeDTO relativeDTO = find(relativeOne.getId(), relativeSomeOne.getId(), relativeLinked.getId());
        RelationDTO relationDTO = relativeDTO.getRelationDTO();
        Relation relation = relationDTO.marshall();
        return relation;
    }

    @Override
    public RelativeDTO find(Long relativeOneId, Long relativeSomeOneId, Long relativeLinkedId) {

        if(relativeService.findById(relativeOneId).isPresent()
                && relativeService.findById(relativeSomeOneId).isPresent()
                && relativeService.findById(relativeLinkedId).isPresent()){

            RelativeDTO relativeSomeOneDTO = find(relativeService.findById(relativeOneId).get(),
                    relativeService.findById(relativeSomeOneId).get(),
                    relativeService.findById(relativeLinkedId).get());
            return relativeSomeOneDTO;
        }

        return null;
    }

    @Override
    public RelativeDTO find(RelativeDTO relativeOne, RelativeDTO relativeSomeOne, RelativeDTO relativeLinked) {
        //Sequence 1.
        // If User trying to see his own relationship
        // with relatives he/she has added the people as relatives.
        // In this case we don't required to do any calculation for making relations.
        if(relativeOne.getPersonDTO().getId() == relativeLinked.getPersonDTO().getId()){
            return relativeSomeOne;
        }


        //Sequence 2.
        //If PersonOne trying to find relation with some other person and that other person is
        //already in PersonOne relatives list then we will get the relationship from his list.
        if((relativeOne != null && relativeSomeOne != null) && isExistingRelative(relativeSomeOne.getPersonDTO().getId(), relativeOne.getPersonDTO())){
            return findRelativeByPersonId(relativeSomeOne.getPersonDTO().getId(), relativeOne.getPersonDTO()).get();
        } else {
            //More Sequence inside this method.
            RelationDTO relationWithSomeOne = calculate(relativeOne, relativeSomeOne, relativeLinked);
            relativeSomeOne.setRelationDTO(relationWithSomeOne);
            return relativeSomeOne;
        }
    }

    @Override
    public RelativeDTO addRelative(Long relativeOneId, Long relativeSomeOneId, Long relativeLinkedId) {
        if(relativeService.findById(relativeOneId).isPresent()
                && relativeService.findById(relativeSomeOneId).isPresent()
                && relativeService.findById(relativeLinkedId).isPresent()){


            RelativeDTO relativeSomeOneDTO = find(relativeOneId, relativeSomeOneId, relativeLinkedId);
            RelativeDTO relativeOneDTO = relativeService.findById(relativeOneId).get();
            relativeOneDTO.getPersonDTO().addRelative(relativeSomeOneDTO);

            return relativeOneDTO;
        }
        return null;
    }


    @Override
    public RelativeDTO addRelative(RelativeDTO relativeOne, RelativeDTO relativeSomeOne, RelativeDTO relativeLinked) {
        if(relativeOne != null && relativeSomeOne != null && relativeLinked != null){
            RelativeDTO relativeSomeOneDTO = find(relativeOne.getId(), relativeSomeOne.getId(), relativeLinked.getId());
            RelativeDTO relativeOneDTO = relativeService.findById(relativeOne.getId()).get();
            relativeOneDTO.getPersonDTO().addRelative(relativeSomeOneDTO);

            return relativeOneDTO;
        }
        return null;
    }





    /****************************************************
     *                                                  *
     *                                                  *
     *                                                  *
     *                                                  *
     *          RELATION CALCULATION BELOW              *
     *                                                  *
     *                                                  *
     *                                                  *
     *                                                  *
     ****************************************************/





    /**
     *
     * @param relativeOne
     * @param relativeSomeOne
     * @param relativeLinked
     * @return
     */
    private RelationDTO calculate(RelativeDTO relativeOne, RelativeDTO relativeSomeOne, RelativeDTO relativeLinked) {
        int x = 0;
        int y = 0;


        //Finding and making positions for the new relationship
        x = relativeOne.getRelationDTO().getX() + relativeSomeOne.getRelationDTO().getX();
        y = relativeOne.getRelationDTO().getY() + relativeSomeOne.getRelationDTO().getY();

        //Sequence 3.
        // If PersonOne is a sibling of LinkedPerson then the
        // Parent Relationship (Position) with the PersonOne would be same as between LinkedPerson.
        if (relativeOne.getRelationDTO().isSibling() && relativeSomeOne.getRelationDTO().getY() > 0) {
            x = relativeLinked.getRelationDTO().getX();
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

        RelationDTO result = new RelationDTO(x, y, relativeSomeOne.getRelationDTO().getSex(),
                false, inLaw, isSibling) ;
        result.setOwner(relativeSomeOne.getRelationDTO().isOwner());


        Relation relation = result.marshall();
        relation.setId(0);

        RelationUtil relationUtil = RelationUtil.getInstance();
        String newRelationName = relationUtil.getRelations().get(relation);
        result.setName(newRelationName);

        //If the resulting relation is not making any sense then
        // change its ownership (switch the Husband/Wife property) and then try to match
        if (!relationUtil.getRelations().containsKey(result)) {
            result.setOwner(!relativeSomeOne.getRelationDTO().isOwner());
        }

        System.out.println(relativeOne.getPersonDTO().getName() + "\t" + relativeOne.getRelationDTO());
        System.out.println(relativeSomeOne.getPersonDTO().getName() + "\t" + relativeSomeOne.getRelationDTO());
        System.out.println("RESULT" + "\t" + result);
        System.out.println("=============================================");

        return result;
    }

    public boolean isInLaw(RelativeDTO relativeOne, RelativeDTO relativeSomeOne, RelativeDTO relativeLinked) {
        RelationDTO personOneRelation = relativeOne.getRelationDTO();
        RelationDTO personSomeoneRelation = relativeSomeOne.getRelationDTO();

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


    public boolean isSibling(RelativeDTO relativeOne, RelativeDTO relativeSomeOne, RelativeDTO relativeLinked) {
        RelationDTO personOneRelation = relativeOne.getRelationDTO();
        RelationDTO personSomeoneRelation = relativeSomeOne.getRelationDTO();

        if (personOneRelation.isSibling() && personSomeoneRelation.isSibling()) {
            return true;
        } else {

            return false;
        }
    }



    public Optional<RelativeDTO> findRelativeByPersonId(Long personId, PersonDTO linkedPerson){
        for (RelativeDTO relative: linkedPerson.getRelatives()){
            if(relative.getPersonDTO().getId() == personId){
                return Optional.of(relative);
            }
        }
        return Optional.empty();
    }

    public boolean isExistingRelative(Long personId, PersonDTO personDTO){
        if(personDTO.getRelatives() != null) {
            for (RelativeDTO relative : personDTO.getRelatives()) {
                if (relative.getPersonDTO().getId() == personId) {
                    return true;
                }
            }
        }
        return false;
    }
}
