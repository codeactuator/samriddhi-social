package com.codeactuator.samriddhi;

import com.codeactuator.samriddhi.services.RelationService;
import com.codeactuator.samriddhi.services.impl.RelationServiceImpl;

public class App {



    public static void main(String args[]){
        RelationService relationService = new RelationServiceImpl();

        relationService.find("NEHA", "ANJALI", "SHEKHAR");
        relationService.find("NEHA", "ROHIT", "SHEKHAR");
        relationService.find("NEHA", "SHRAVAN", "SHEKHAR");
    }
}

