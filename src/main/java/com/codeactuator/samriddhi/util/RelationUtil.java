package com.codeactuator.samriddhi.util;

import com.codeactuator.samriddhi.constants.Relations;
import com.codeactuator.samriddhi.domain.Person;
import com.codeactuator.samriddhi.domain.Relation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RelationUtil {

    Map<Relation, String> relations = new HashMap();

    private static RelationUtil relationUtil;

    public static RelationUtil getInstance(){
        if(relationUtil == null){
            relationUtil = new RelationUtil();
            return relationUtil;
        }else{
            return relationUtil;
        }
    }

    private RelationUtil() {

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
    }

    public Map<Relation, String> getRelations() {
        return relations;
    }

    public void setRelations(Map<Relation, String> relations) {
        this.relations = relations;
    }

    public Relation getRelationByName(Relations relationEnum){
        Set<Relation> relations = this.relations.keySet();
        for(Relation relation: relations){
            if(relation.getName().equalsIgnoreCase(relationEnum.getRelation())){
                return relation;
            }
        }
        return null;
    }


}