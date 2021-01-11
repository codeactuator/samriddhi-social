package com.codeactuator.samriddhi.services;

import com.codeactuator.samriddhi.constants.Relations;
import com.codeactuator.samriddhi.domain.Person;
import com.codeactuator.samriddhi.domain.Relation;
import com.codeactuator.samriddhi.domain.Relative;
import com.codeactuator.samriddhi.services.impl.RelativeCalculationServiceImpl;
import com.codeactuator.samriddhi.util.RelationUtil;
import com.codeactuator.samriddhi.util.TestData;
import org.junit.Before;
import org.junit.Test;

public class RelativeCalculationServiceTests {


    private RelativeCalculationService relativeCalculationService;
    private TestData testData;
    private RelationUtil relationUtil;

    @Before
    public void init(){
        testData = new TestData();
        relationUtil = RelationUtil.getInstance();
        relativeCalculationService = new RelativeCalculationServiceImpl();
        assert (testData.getPeople() != null);

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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative opGupta = testData.getRelativeByName("OP GUPTA", shekharPerson);


        Relation relation = relativeCalculationService.find(shekhar, opGupta, shekhar);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.FATHER.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == true);
        assert (relation.isOwner() == true);
        assert (relation.getY() == 1);

    }


    /**
     * Testing my own relationship with my own Mother
     */
    @Test
    public void testMother(){
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative ushaDevi = testData.getRelativeByName("USHA DEVI", shekharPerson);

        Relation relation = relativeCalculationService.find(shekhar, ushaDevi, shekhar);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.MOTHER.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative shravan = testData.getRelativeByName("SHRAVAN", shekharPerson);

        Relation relation = relativeCalculationService.find(shekhar, shravan, shekhar);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHER.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative chhoti = testData.getRelativeByName("CHHOTI", shekharPerson);


        Relation relation = relativeCalculationService.find(shekhar, chhoti, shekhar);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.SISTER.getRelation()));
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
     * Testing relationship of my own brother with my own Father.
     */
    @Test
    public void testSiblingWithFather(){
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative shravan = testData.getRelativeByName("SHRAVAN", shekharPerson);
        Relative opGupta = testData.getRelativeByName("OP GUPTA", shekharPerson);

        Relation relation = relativeCalculationService.find(shravan, opGupta, shekhar);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.FATHER.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == true);
        assert (relation.isOwner() == true);
        assert (relation.getSex() == 1);
    }


    /**
     * Testing relationship of my own brother with with my own Mother.
     */
    @Test
    public void testSiblingWithMother(){
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative shravan = testData.getRelativeByName("SHRAVAN", shekharPerson);
        Relative ushaDevi = testData.getRelativeByName("USHA DEVI", shekharPerson);

        Relation relation = relativeCalculationService.find(shravan, ushaDevi, shekhar);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.MOTHER.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == true);
        assert (relation.isOwner() == false);
        assert (relation.getSex() == 0);
    }


    /**
     * Testing my own relationship with my Uncle who is already added to my relatives list.
     */
    @Test
    public void testUncle(){
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative ashokKumar = testData.getRelativeByName("ASHOK KUMAR", shekharPerson);

        Relation relation = relativeCalculationService.find(shekhar, ashokKumar, shekhar);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.UNCLE.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative lalmuniDevi = testData.getRelativeByName("LALMUNI DEVI", shekharPerson);

        Relation relation = relativeCalculationService.find(shekhar, lalmuniDevi, shekhar);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.AUNTY.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative ranjeet = testData.getRelativeByName("RANJEET", shekharPerson);
        Relative ashokKumar = testData.getRelativeByName("ASHOK KUMAR", ranjeet.getPerson());

        Relation relation = relativeCalculationService.find(shekhar, ashokKumar, ranjeet);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.UNCLE.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative ranjeet = testData.getRelativeByName("RANJEET", shekharPerson);
        Relative lalmuniDevi = testData.getRelativeByName("LALMUNI DEVI", ranjeet.getPerson());

        Relation relation = relativeCalculationService.find(shekhar, lalmuniDevi, ranjeet);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.AUNTY.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative ranjeet = testData.getRelativeByName("RANJEET", shekharPerson);
        Relative opGupta = testData.getRelativeByName("OP GUPTA", shekharPerson);

        Relation relation = relativeCalculationService.find(ranjeet, opGupta, shekhar);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.UNCLE.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative ranjeet = testData.getRelativeByName("RANJEET", shekharPerson);
        Relative ushaDevi = testData.getRelativeByName("USHA DEVI", shekharPerson);

        Relation relation = relativeCalculationService.find(ranjeet, ushaDevi, shekhar);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.AUNTY.getRelation()));
        assert (relation.isInlaw() == false);
        assert (relation.isSibling() == false);
        assert (relation.isOwner() == false);
        assert (relation.getSex() == 0);
    }








    /**
     * Testing relationship of sibling Brother with sibling Brother.
     */
    @Test
    public void testSiblingBrotherWithSiblingBrother(){
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative shravan = testData.getRelativeByName("SHRAVAN", shekharPerson);
        Relative sujeet = testData.getRelativeByName("SUJEET", shekharPerson);

        Relation relation = relativeCalculationService.find(sujeet, shravan, shekhar);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHER.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative chhoti = testData.getRelativeByName("CHHOTI", shekharPerson);
        Relative sujeet = testData.getRelativeByName("SUJEET", shekharPerson);

        Relation relation = relativeCalculationService.find(sujeet, chhoti, shekhar);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.SISTER.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative ranjeet = testData.getRelativeByName("RANJEET", shekharPerson);

        Relation shekharAsRanjeetNonSiblingBrother = relativeCalculationService.find(shekhar, ranjeet, shekhar);
        shekhar.setRelation(shekharAsRanjeetNonSiblingBrother);

        Relative amrita = testData.getRelativeByName("AMRITA", ranjeet.getPerson());

        Relation relation = relativeCalculationService.find(shekhar, amrita, ranjeet);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.SISTER_NON_SIBLING.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative sanjeet = testData.getRelativeByName("SANJEET", shekharPerson);


        Relation relation = relativeCalculationService.find(shekhar, sanjeet, shekhar);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHER_NON_SIBLING.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative shravan = testData.getRelativeByName("SHRAVAN", shekharPerson);
        Relative ranjeet = testData.getRelativeByName("RANJEET", shekharPerson);

        Relation relation = relativeCalculationService.find(shravan, ranjeet, shekhar);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHER_NON_SIBLING.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");

        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative ranjeet = testData.getRelativeByName("RANJEET", shekharPerson);

        Relative amrita = testData.getRelativeByName("AMRITA", ranjeet.getPerson());
        Relative shravan = testData.getRelativeByName("SHRAVAN", shekharPerson);
        shravan.getRelation().setSibling(ranjeet.getRelation().isSibling());

        Relation relation = relativeCalculationService.find(shravan, amrita, ranjeet);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.SISTER_NON_SIBLING.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative sanjeet = testData.getRelativeByName("SANJEET", shekharPerson);
        Relative ranjeet = testData.getRelativeByName("RANJEET", shekharPerson);

        Relation relation = relativeCalculationService.find(ranjeet, sanjeet, shekhar);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHER_NON_SIBLING.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative ranjeet = testData.getRelativeByName("RANJEET", shekharPerson);
        Relative ajit = testData.getRelativeByName("AJIT", ranjeet.getPerson());


        Relation relation = relativeCalculationService.find(ranjeet, ajit, shekhar);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHER.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative anjali = testData.getRelativeByName("ANJALI", shekharPerson);


        Relation relation = relativeCalculationService.find(shekhar, anjali, shekhar);
        System.out.println(relation);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.WIFE.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative anjali = testData.getRelativeByName("ANJALI", shekharPerson);
        Relative sujeet = testData.getRelativeByName("SUJEET", shekharPerson);

        Relation relation = relativeCalculationService.find(sujeet, anjali, shekhar);
        System.out.println(relation);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHERS_WIFE.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative anjali = testData.getRelativeByName("ANJALI", shekharPerson);
        Relative ranjeet = testData.getRelativeByName("RANJEET", shekharPerson);

        Relation relation = relativeCalculationService.find(ranjeet, anjali, shekhar);
        System.out.println(relation);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHERS_WIFE_NON_SIBLING.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative praveenKumar = testData.getRelativeByName("PRAVEEN KUMAR", shekharPerson);


        Relation relation = relativeCalculationService.find(shekhar, praveenKumar, shekhar);
        System.out.println(relation);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.FATHER_IN_LAW.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative nilamDevi = testData.getRelativeByName("NILAM DEVI", shekharPerson);


        Relation relation = relativeCalculationService.find(shekhar, nilamDevi, shekhar);
        System.out.println(relation);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.MOTHER_IN_LAW.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative nilamDevi = testData.getRelativeByName("NILAM DEVI", shekharPerson);
        Relative shravan = testData.getRelativeByName("SHRAVAN", shekharPerson);

        Relation relation = relativeCalculationService.find(shravan, nilamDevi, shekhar);
        System.out.println(relation);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.MOTHER_IN_LAW.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative sanjeet = testData.getRelativeByName("SANJEET", shekharPerson);
        Relative nilamDevi = testData.getRelativeByName("NILAM DEVI", shekharPerson);

        Relation relation = relativeCalculationService.find(sanjeet, nilamDevi, shekhar);
        System.out.println(relation);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.MOTHER_IN_LAW_NON_SIBLING.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative sanjeet = testData.getRelativeByName("SANJEET", shekharPerson);
        Relative praveenKumar = testData.getRelativeByName("PRAVEEN KUMAR", shekharPerson);

        Relation relation = relativeCalculationService.find(sanjeet, praveenKumar, shekhar);
        System.out.println(relation);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.FATHER_IN_LAW_NON_SIBLING.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative rohit = testData.getRelativeByName("ROHIT", shekharPerson);

        Relation relation = relativeCalculationService.find(shekhar, rohit, shekhar);
        System.out.println(relation);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHER_IN_LAW.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative neha = testData.getRelativeByName("NEHA", shekharPerson);

        Relation relation = relativeCalculationService.find(shekhar, neha, shekhar);
        System.out.println(relation);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.SISTER_IN_LAW.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative shravan = testData.getRelativeByName("SHRAVAN", shekharPerson);
        Relative neha = testData.getRelativeByName("NEHA", shekharPerson);

        Relation relation = relativeCalculationService.find(shravan, neha, shekhar);
        System.out.println(relation);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.SISTER_IN_LAW.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative sanjeet = testData.getRelativeByName("SANJEET", shekharPerson);
        Relative neha = testData.getRelativeByName("NEHA", shekharPerson);

        Relation relation = relativeCalculationService.find(sanjeet, neha, shekhar);
        System.out.println(relation);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.SISTER_IN_LAW_NON_SIBLING.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative sanjeet = testData.getRelativeByName("SANJEET", shekharPerson);
        Relative rohit = testData.getRelativeByName("ROHIT", shekharPerson);

        Relation relation = relativeCalculationService.find(sanjeet, rohit, shekhar);
        System.out.println(relation);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHER_IN_LAW_NON_SIBLING.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative shravan = testData.getRelativeByName("SHRAVAN", shekharPerson);
        Relative neha = testData.getRelativeByName("NEHA", shekharPerson);

        Relation relation = relativeCalculationService.find(neha, shravan, shekhar);
        System.out.println(relation);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHER_IN_LAW.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative sanjeet = testData.getRelativeByName("SANJEET", shekharPerson);
        Relative neha = testData.getRelativeByName("NEHA", shekharPerson);

        Relation relation = relativeCalculationService.find(neha, sanjeet, shekhar);
        System.out.println(relation);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.BROTHER_IN_LAW_NON_SIBLING.getRelation()));
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
        Person shekharPerson = testData.getPersonByName("SHEKHAR");
        Relative shekhar = testData.getRelativeByName("SHEKHAR", shekharPerson);
        Relative ranjeet = testData.getRelativeByName("RANJEET", shekharPerson);
        Relative kshitij = testData.getRelativeByName("KSHITIJ", ranjeet.getPerson());
        shekhar.getRelation().setSibling(ranjeet.getRelation().isSibling());

        Relation relation = relativeCalculationService.find(shekhar, kshitij, ranjeet);
        System.out.println(relation);
        assert (relationUtil.getRelations().get(relation).equalsIgnoreCase(Relations.SISTERS_HUSBAND_NON_SIBLING.getRelation()));
        assert (relation.isInlaw() == true);
        assert (relation.isSibling() == false);
        assert (relation.isOwner() == false);
        assert (relation.getSex() == 1);
    }
}
