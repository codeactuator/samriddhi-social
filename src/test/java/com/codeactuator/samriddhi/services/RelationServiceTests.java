package com.codeactuator.samriddhi.services;

import com.codeactuator.samriddhi.constants.Relations;
import com.codeactuator.samriddhi.domain.Person;
import com.codeactuator.samriddhi.domain.Relation;
import com.codeactuator.samriddhi.domain.Relative;
import com.codeactuator.samriddhi.services.impl.RelationServiceImpl;
import com.codeactuator.samriddhi.util.DataUtil;
import org.junit.Before;
import org.junit.Test;

import javax.xml.crypto.Data;

public class RelationServiceTests {

    private RelationService relationService;

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

    @Before
    public void init(){
        relationService = new RelationServiceImpl();
        assert (DataUtil.getRelations() != null);
        assert (DataUtil.getPeople() != null);

    }



    /**
     **************************************
     *
     *        OWN RELATIONSHIPS
     *
     * ************************************
     */


    /**
     * Testing my own relationship with my own Father
     */
    @Test
    public void testFather(){

        Person shekhar = new Person();
        shekhar.setId(1);
        shekhar.setName("SHEKHAR");

        Relative shekharRelative = new Relative();
        shekharRelative.setId(new Long(1));
        shekharRelative.setPerson(shekhar);
        shekharRelative.setRelation(me);

        Person opGupta = new Person();
        opGupta.setId(3);
        opGupta.setName("OP GUPTA");

        Relative opGuptaRelative = new Relative();
        opGuptaRelative.setId(new Long(3));
        opGuptaRelative.setPerson(opGupta);
        opGuptaRelative.setRelation(father);


        Relation relation = relationService.find(shekharRelative, opGuptaRelative, shekharRelative);
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.FATHER.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == true);
        assert (relation.isOwner() == true);
        assert (relation.getY() == 1);

    }



    /**
     * Testing relationship of my own brother with my own Father.
     */
    @Test
    public void testSiblingWithFather(){

        Person shekhar = new Person();
        shekhar.setId(1);
        shekhar.setName("SHEKHAR");

        Relative shekharRelative = new Relative();
        shekharRelative.setId(new Long(1));
        shekharRelative.setPerson(shekhar);
        shekharRelative.setRelation(me);

        Person sujeet = new Person();
        sujeet.setId(7);
        sujeet.setName("SUJEET");

        Relative sujeetRelative = new Relative();
        sujeetRelative.setId(new Long(7));
        sujeetRelative.setPerson(sujeet);
        sujeetRelative.setRelation(brother);

        Person opGupta = new Person();
        opGupta.setId(3);
        opGupta.setName("OP GUPTA");

        Relative opGuptaRelative = new Relative();
        opGuptaRelative.setId(new Long(3));
        opGuptaRelative.setPerson(opGupta);
        opGuptaRelative.setRelation(father);

        shekhar.addRelative(sujeetRelative);
        shekhar.addRelative(opGuptaRelative);

        sujeet.addRelative(shekharRelative);
        opGupta.addRelative(shekharRelative);



        Relation relation = relationService.find(sujeetRelative, opGuptaRelative, shekharRelative);
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.FATHER.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == true);
        assert (relation.isOwner() == true);
        assert (relation.getSex() == 1);
    }

    /**
     * Testing my own relationship with my own Mother
     */
    @Test
    public void testMother(){
        Relation relation = relationService.find("SHEKHAR", "USHA DEVI", "SHEKHAR");
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.MOTHER.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == true);
        assert (relation.isOwner() == false);
        assert (relation.getSex() == 0);
    }


    /**
     * Testing my own  relationship with my own Brother
     */
    @Test
    public void testBrother(){
        Relation relation = relationService.find("SHEKHAR", "SHRAVAN", "SHEKHAR");
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHER.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == true);
        assert (relation.isOwner() == true);
        assert (relation.getSex() == 1);
    }


    /**
     * Testing my own relationship with my own Sister
     */
    @Test
    public void testSister(){
        Relation relation = relationService.find("SHEKHAR", "CHHOTI", "SHEKHAR");
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.SISTER.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == true);
        assert (relation.isOwner() == true);
        assert (relation.getSex() == 0);
    }



    /**
     ************************************
     *
     *       SIBLING RELATIONSHIP
     *
     ************************************
     */

    /**
     * Testing my own relationship with my Uncle who is already added to my relatives list.
     */
    @Test
    public void testUncle(){
        Relation relation = relationService.find("SHEKHAR", "ASHOK KUMAR", "SHEKHAR");
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.UNCLE.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == false);
        assert (relation.isOwner() == true);
        assert (relation.getSex() == 1);
    }

    /**
     * Testing my own relationship with my Aunty who is already added to my relatives list.
     */
    @Test
    public void testAunty(){
        Relation relation = relationService.find("SHEKHAR", "LALMUNI DEVI", "SHEKHAR");
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.AUNTY.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == false);
        assert (relation.isOwner() == false);
        assert (relation.getSex() == 0);
    }


    /**
     * Testing my own relationship with my Uncle who is not added to my relatives list.
     */
    @Test
    public void testUncleWithLinkedPerson(){
        Relation relation = relationService.find("SHEKHAR", "ASHOK KUMAR", "RANJEET");
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.UNCLE.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == false);
        assert (relation.isOwner() == true);
        assert (relation.getSex() == 1);
    }

    /**
     * Testing my own relationship with my Aunty who is not added to my relatives list.
     */
    @Test
    public void testAuntyWithLinkedPerson(){
        Relation relation = relationService.find("SHEKHAR", "LALMUNI DEVI", "RANJEET");
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.AUNTY.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == false);
        assert (relation.isOwner() == false);
        assert (relation.getSex() == 0);
    }

    /**
     * Testing my non sibling brother relationship with my Father
     */
    @Test
    public void testNonSiblingBrotherWithFather(){
        Relation relation = relationService.find("RANJEET", "OP GUPTA", "SHEKHAR");
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.UNCLE.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == false);
        assert (relation.isOwner() == true);
        assert (relation.getSex() == 1);
    }

    /**
     * Testing my non sibling brother relationship with my Mother
     */
    @Test
    public void testNonSiblingBrotherWithMother(){
        Relation relation = relationService.find("RANJEET", "USHA DEVI", "SHEKHAR");
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.AUNTY.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == false);
        assert (relation.isOwner() == false);
        assert (relation.getSex() == 0);
    }




    /**
     * Testing relationship of my own brother with with my own Mother.
     */
    @Test
    public void testSiblingWithMother(){
        Relation relation = relationService.find("SHRAVAN", "USHA DEVI", "SHEKHAR");
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.MOTHER.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == true);
        assert (relation.isOwner() == false);
        assert (relation.getSex() == 0);
    }




    /**
     * Testing relationship of sibling Brother with sibling Brother.
     */
    @Test
    public void testSiblingBrotherWithSiblingBrother(){
        Relation relation = relationService.find("SUJEET", "SHRAVAN", "SHEKHAR");
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHER.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == true);
        assert (relation.isOwner() == true);
        assert (relation.getSex() == 1);
    }


    /**
     * Testing my sibling brother relationship with my non sibling sister.
     */
    @Test
    public void testSiblingBrotherWithSiblingSister(){
        Relation relation = relationService.find("SUJEET", "CHHOTI", "SHEKHAR");
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.SISTER.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == true);
        assert (relation.isOwner() == true);
        assert (relation.getSex() == 0);
    }


    /**
     * *****************************************
     *
     *      NON SIBLING RELATIONSHIP
     *
     * *****************************************
     */

    /**
     * Testing my own relationship with non sibling sister.
     */
    @Test
    public void testNonSiblingSister(){
        Relation relation = relationService.find("SHEKHAR", "AMRITA", "RANJEET");
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.SISTER_NON_SIBLING.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == false);
        assert (relation.isOwner() == true);
        assert (relation.getSex() == 0);
    }


    /**
     * Testing my own relationship with non sibling brother.
     */
    @Test
    public void testNonSiblingBrother(){
        Relation relation = relationService.find("SHEKHAR", "SANJEET", "SHEKHAR");
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHER_NON_SIBLING.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == false);
        assert (relation.isOwner() == true);
        assert (relation.getSex() == 1);
    }


    /**
     * Testing my own brother relationship with my non sibling brother.
     */
    @Test
    public void testNonSiblingBrotherWithSiblingBrother(){
        Relation relation = relationService.find("SANJEET", "SHRAVAN", "SHEKHAR");
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHER_NON_SIBLING.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == false);
        assert (relation.isOwner() == true);
        assert (relation.getSex() == 1);
    }


    /**
     * Testing my own brother relationship with my non sibling sister.
     */
    @Test
    public void testSiblingBrotherWithNonSiblingSister(){
        Relation relation = relationService.find("SHRAVAN", "AMRITA", "RANJEET");
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.SISTER_NON_SIBLING.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == false);
        assert (relation.isOwner() == true);
        assert (relation.getSex() == 0);
    }



    /**
     * Testing my none sibling brother relationship with my non sibling brother.
     * And that two none sibling brothers are own brothers in reality but in
     * system PersonOne (Brother first) has not added his own brother (Brother second)
     * as a relatives yet.
     *
     * In this case they will be considered as NON_SIBLING_BROTHER.
     */
    @Test
    public void testNoneSiblingBrotherWithNonSiblingBrother(){
        Relation relation = relationService.find("RANJEET", "SANJEET", "SHEKHAR");
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHER_NON_SIBLING.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == false);
        assert (relation.isOwner() == true);
        assert (relation.getSex() == 1);
    }


    /**
     * Testing my none sibling brother relationship with my non sibling brother.
     * And that two none sibling brothers are own brothers in reality and in
     * system PersonOne (Brother first) has added his own brother (Brother second)
     * as a relatives.
     *
     * In this case they will be considered as BROTHER.
     */
    @Test
    public void testNoneSiblingBrotherWithNonSiblingBrother_case2(){
        Relation relation = relationService.find("RANJEET", "AJIT", "SHEKHAR");
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHER.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == true);
        assert (relation.isOwner() == true);
        assert (relation.getSex() == 1);
    }


    /**
     * *****************************************
     *
     *      INLAW RELATIONSHIP
     *
     * *****************************************
     */


    /**
     * Testing my relationship with Wife
     */
    @Test
    public void testWife(){
        Relation relation = relationService.find("SHEKHAR", "ANJALI", "SHEKHAR");
        System.out.println(relation);
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.WIFE.getRelation()));
        assert (relation.isInlaw() == true);
        assert (relation.isSibling() == true);
        assert (relation.isOwner() == false);
        assert (relation.getSex() == 0);
    }


    /**
     * Testing my Sibling Brother relationship with Wife
     */
    @Test
    public void testSiblingBrotherWithWife(){
        Relation relation = relationService.find("SHRAVAN", "ANJALI", "SHEKHAR");
        System.out.println(relation);
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHERS_WIFE.getRelation()));
        assert (relation.isInlaw() == true);
        assert (relation.isSibling() == true);
        assert (relation.isOwner() == false);
        assert (relation.getSex() == 0);
    }


    /**
     * Testing my Non Sibling Brother relationship with Wife
     */
    @Test
    public void testNonSiblingBrotherWithWife(){
        Relation relation = relationService.find("RANJEET", "ANJALI", "SHEKHAR");
        System.out.println(relation);
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHERS_WIFE_NON_SIBLING.getRelation()));
        assert (relation.isInlaw() == true);
        assert (relation.isSibling() == false);
        assert (relation.isOwner() == false);
        assert (relation.getSex() == 0);
    }


    /**
     * Testing my own relationship with my Father in-law
     */
    @Test
    public void testFatherInLaw(){
        Relation relation = relationService.find("SHEKHAR", "PRAVEEN KUMAR", "SHEKHAR");
        System.out.println(relation);
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.FATHER_IN_LAW.getRelation()));
        assert (relation.isInlaw() == true);
        assert (relation.isSibling() == true);
        assert (relation.isOwner() == true);
        assert (relation.getSex() == 1);
    }

    /**
     * Testing my own relationship with my Mother in-law
     */
    @Test
    public void testMotherInLaw(){
        Relation relation = relationService.find("SHEKHAR", "NILAM DEVI", "SHEKHAR");
        System.out.println(relation);
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.MOTHER_IN_LAW.getRelation()));
        assert (relation.isInlaw() == true);
        assert (relation.isSibling() == true);
        assert (relation.isOwner() == false);
        assert (relation.getSex() == 0);
    }

    /**
     * Testing my Sibling Brother relationship with my Mother in-law
     */
    @Test
    public void testSiblingBrotherWithMotherInLaw(){
        Relation relation = relationService.find("SHRAVAN", "NILAM DEVI", "SHEKHAR");
        System.out.println(relation);
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.MOTHER_IN_LAW.getRelation()));
        assert (relation.isInlaw() == true);
        assert (relation.isSibling() == true);
        assert (relation.isOwner() == false);
        assert (relation.getSex() == 0);
    }

    /**
     * Testing my Non Sibling Brother relationship with my Mother in-law
     */
    @Test
    public void testNonSiblingBrotherWithMotherInLaw(){
        Relation relation = relationService.find("RANJEET", "NILAM DEVI", "SHEKHAR");
        System.out.println(relation);
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.MOTHER_IN_LAW_NON_SIBLING.getRelation()));
        assert (relation.isInlaw() == true);
        assert (relation.isSibling() == false);
        assert (relation.isOwner() == false);
        assert (relation.getSex() == 0);
    }


    /**
     * Testing my Non Sibling Brother relationship with my Father in-law
     */
    @Test
    public void testNonSiblingBrotherWithFatherInLaw(){
        Relation relation = relationService.find("RANJEET", "PRAVEEN KUMAR", "SHEKHAR");
        System.out.println(relation);
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.FATHER_IN_LAW_NON_SIBLING.getRelation()));
        assert (relation.isInlaw() == true);
        assert (relation.isSibling() == false);
        assert (relation.isOwner() == true);
        assert (relation.getSex() == 1);
    }

    /**
     * Testing my own relationship with my Brother in-law
     */
    @Test
    public void testBrotherInLaw(){
        Relation relation = relationService.find("SHEKHAR", "ROHIT", "SHEKHAR");
        System.out.println(relation);
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHER_IN_LAW.getRelation()));
        assert (relation.isInlaw() == true);
        assert (relation.isSibling() == true);
        assert (relation.isOwner() == true);
        assert (relation.getSex() == 1);
    }

    /**
     * Testing my own relationship with my Sister in-law
     */
    @Test
    public void testSisterInLaw(){
        Relation relation = relationService.find("SHEKHAR", "NEHA", "SHEKHAR");
        System.out.println(relation);
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.SISTER_IN_LAW.getRelation()));
        assert (relation.isInlaw() == true);
        assert (relation.isSibling() == true);
        assert (relation.isOwner() == true); //SISTER'S HUSBAND IS NOT THE OWNER OF HOUSE FROM SISTER'S HOUSE SIDE.
        assert (relation.getSex() == 0);
    }

    /**
     * Testing my own Brother relationship with my Sister in-law.
     * Expected Result: SISTER IN-LAW
     */
    @Test
    public void testSiblingBrotherWithSisterInLaw(){
        Relation relation = relationService.find("SHRAVAN", "NEHA", "SHEKHAR");
        System.out.println(relation);
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.SISTER_IN_LAW.getRelation()));
        assert (relation.isInlaw() == true);
        assert (relation.isSibling() == true);
        assert (relation.isOwner() == true); //SISTER'S HUSBAND IS NOT THE OWNER OF HOUSE FROM SISTER'S HOUSE SIDE.
        assert (relation.getSex() == 0);
    }

    /**
     * Testing my Non Sibling Brother relationship with my Sister in-law.
     * Expected Result: SISTER IN-LAW NON SIBLING
     */
    @Test
    public void testNonSiblingBrotherWithSisterInLaw(){
        Relation relation = relationService.find("RANJEET", "NEHA", "SHEKHAR");
        System.out.println(relation);
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.SISTER_IN_LAW_NON_SIBLING.getRelation()));
        assert (relation.isInlaw() == true);
        assert (relation.isSibling() == false);
        assert (relation.isOwner() == true);
        assert (relation.getSex() == 0);
    }

    /**
     * Testing my Non Sibling Brother relationship with my Brother in-law.
     * Expected Result: BROTHER IN-LAW NON SIBLING
     */
    @Test
    public void testNonSiblingBrotherWithBrotherInLaw(){
        Relation relation = relationService.find("RANJEET", "ROHIT", "SHEKHAR");
        System.out.println(relation);
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHER_IN_LAW_NON_SIBLING.getRelation()));
        assert (relation.isInlaw() == true);
        assert (relation.isSibling() == false);
        assert (relation.isOwner() == true);
        assert (relation.getSex() == 1);
    }


    /**
     * Testing my Sister In-Law relationship with my Sibling Brother.
     * Expected Result: BROTHER IN-LAW
     */
    @Test
    public void testSisterInLawWithSiblingBrother(){
        Relation relation = relationService.find("NEHA", "SHRAVAN", "SHEKHAR");
        System.out.println(relation);
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHER_IN_LAW.getRelation()));
        assert (relation.isInlaw() == true);
        assert (relation.isSibling() == true);
        assert (relation.isOwner() == true);
        assert (relation.getSex() == 1);
    }

    /**
     * Testing my Sister In-Law relationship with my Non Sibling Brother.
     * Expected Result: BROTHER IN-LAW (Same relation as Sibling Brother)
     */
    @Test
    public void testSisterInLawWithNonSiblingBrother(){
        Relation relation = relationService.find("NEHA", "RANJEET", "SHEKHAR");
        System.out.println(relation);
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHER_IN_LAW_NON_SIBLING.getRelation()));
        assert (relation.isInlaw() == true);
        assert (relation.isSibling() == false);
        assert (relation.isOwner() == true);
        assert (relation.getSex() == 1);
    }

    /**
     * Testing my relationship with Sister's Husband Non Sibling
     */
    @Test
    public void testSistersHusbandNonSibling(){
        Relation relation = relationService.find("SHRAVAN", "KSHITIJ", "RANJEET");
        System.out.println(relation);
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.SISTERS_HUSBAND_NON_SIBLING.getRelation()));
        assert (relation.isInlaw() == true);
        assert (relation.isSibling() == false);
        assert (relation.isOwner() == false);
        assert (relation.getSex() == 1);
    }
}
