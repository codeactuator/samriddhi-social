package com.codeactuator.samriddhi.services;

import com.codeactuator.samriddhi.constants.Relations;
import com.codeactuator.samriddhi.domain.Relation;
import com.codeactuator.samriddhi.services.impl.RelationServiceImpl;
import com.codeactuator.samriddhi.util.DataUtil;
import org.junit.Before;
import org.junit.Test;

import javax.xml.crypto.Data;

public class RelationServiceTests {

    private RelationService relationService;

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
        Relation relation = relationService.find("SHEKHAR", "OP GUPTA", "SHEKHAR");
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.FATHER.getRelation()));
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
     * Testing relationship of my own brother with my own Father.
     */
    @Test
    public void testSiblingWithFather(){
        Relation relation = relationService.find("SUJEET", "OP GUPTA", "SHEKHAR");
        assert (DataUtil.getRelations().get(relation).equalsIgnoreCase(Relations.FATHER.getRelation()));
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
        Relation relation = relationService.find("SHEKHAR", "AMRITA", "SHEKHAR");
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
        Relation relation = relationService.find("SHRAVAN", "AMRITA", "SHEKHAR");
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
     * Testing my own relationship with my Father in-law
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
}
