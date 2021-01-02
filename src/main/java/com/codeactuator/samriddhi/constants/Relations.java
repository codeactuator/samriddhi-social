package com.codeactuator.samriddhi.constants;

public enum Relations {


    ME("Me"),

    FATHER("Father"),
    MOTHER("Mother"),

    UNCLE("Uncle"),
    AUNTY("Aunty"),

    HUSBAND("Husbabd"),
    WIFE("Wife"),

    BROTHER("Brother"),
    SISTER("Sister"),

    BROTHER_NON_SIBLING("Brother Nonsibling"),
    SISTER_NON_SIBLING("Sister Nonsibling"),

    FATHER_IN_LAW("Father In-Law"),
    MOTHER_IN_LAW("Mother In-Law"),

    FATHER_IN_LAW_NON_SIBLING("Father In-Law Nonsibling"),
    MOTHER_IN_LAW_NON_SIBLING("Mother In-Law Nonsibling"),

    BROTHER_IN_LAW("Brother In-Law"),
    SISTER_IN_LAW("Sister In-Law"),

    BROTHER_IN_LAW_NON_SIBLING("Brother In-Law Nonsibling"),
    SISTER_IN_LAW_NON_SIBLING("Sister In-Law Nonsibling"),

    BROTHERS_WIFE("Brother's Wife"),
    BROTHERS_WIFE_NON_SIBLING("Brother's Wife Nonsibling"),
    SISTERS_HUSBAND("Sister'S Husband"),
    SISTERS_HUSBAND_NON_SIBLING("Sister'S Husband Nonsibling"),

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
