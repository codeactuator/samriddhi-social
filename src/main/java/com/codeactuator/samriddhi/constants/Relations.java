package com.codeactuator.samriddhi.constants;

public enum Relations {


    ME("Me"),

    FATHER("Father"),
    MOTHER("Mother"),

    HUSBAND("Husbabd"),
    WIFE("Wife"),

    BROTHER("Brother"),
    SISTER("Sister"),

    BROTHER_NON_SIBLING("Brother Nonsibling"),
    SISTER_NON_SIBLING("Sister Nonsibling"),

    FATHER_IN_LAW("Father In-Law"),
    MOTHER_IN_LAW("Mother In-Law"),

    BROTHER_IN_LAW("Brother In-Law"),
    SISTER_IN_LAW("Sister In-Law"),

    BROTHERS_WIFE("Brother's Wife"),
    SISTERS_HUSBAND("Sister'S Husband"),

    SON("Son"),
    DOUGHTER("Daughter")
    ;



    private String relation;

    private Relations(String relation){
        this.relation = relation;
    }

    public String getRelation(){
        return this.relation;
    }
}
