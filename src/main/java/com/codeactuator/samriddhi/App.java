package com.codeactuator.samriddhi;

import com.codeactuator.samriddhi.domain.Relation;
import com.codeactuator.samriddhi.services.RelationService;
import com.codeactuator.samriddhi.services.impl.RelationServiceImpl;
import com.codeactuator.samriddhi.util.RelationUtil;

public class App {



    public static void main(String args[]){
        RelationUtil relationUtil = RelationUtil.getInstance();
        Relation relation = new Relation();
        relation.setX(1);
        relation.setY(1);
        relation.setSex(1);
        relation.setInlaw(false);
        relation.setSibling(false);
        relation.setOwner(true);

        String relationName = relationUtil.getRelations().get(relation);
        System.out.println(relationName);
    }
}

