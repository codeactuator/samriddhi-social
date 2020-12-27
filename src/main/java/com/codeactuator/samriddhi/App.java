package com.codeactuator.samriddhi;

import java.util.*;

public class App {

    static Map<Relation, String> relations = new HashMap();
    static Map<String, Person> people = new HashMap();
    static Person shekhar ; //= new Person("SHEKHAR", 1);

    public void init() {

        shekhar = new Person("SHEKHAR", 1);

        //Creating some relationship definition
        Relation me = new Relation(0, 0, 1, true, false, true);

        Relation father = new Relation(0, 1, 1, true, false, true);
        Relation mother = new Relation(0, 1, 0, true, false, true);

        Relation fatherInLaw = new Relation(0, 1, 1, true, true, true);
        Relation motherInLaw = new Relation(0, 1, 0, true, true, true);

        Relation brother = new Relation(1, 0, 1, true, false, true);
        Relation brotherNonSibling = new Relation(1, 0, 1, true, false, false);
        Relation brotherWife = new Relation(1, 0, 0, false, true, false);

        Relation sister = new Relation(1, 0, 0, true, false, true);
        Relation sisterNonSibling = new Relation(1, 0, 0, true, false, false);
        Relation sistersHusband = new Relation(1, 0, 1, false, true, false);

        Relation wife = new Relation(0, 0, 0, false, true, true);
        Relation husband = new Relation(0, 0, 1, true, true, true);

        Relation brotherInLaw = new Relation(1, 0, 1, true, true, true);
        Relation sisterInLaw = new Relation(1, 0, 0, true, true, true);


        Relation son = new Relation(0, -1, 1, true, false, true);
        Relation daughter = new Relation(0, -1, 0, true, false, true);




        relations.put(me, "ME");

        relations.put(father, "FATHER");
        relations.put(mother, "MOTHER");

        relations.put(fatherInLaw, "FATHERINLAW");
        relations.put(motherInLaw, "MOTHERINLAW");

        relations.put(brother, "BROTHER");
        relations.put(brotherNonSibling, "BROTHER_NONE_SIBLING");
        relations.put(brotherWife, "BROTHERSWIFE");

        relations.put(sister, "SISTER");
        relations.put(sisterNonSibling, "SISTER_NONE_SIBLING");
        relations.put(sistersHusband, "SISTERSHUSBAND");

        relations.put(wife, "WIFE");
        relations.put(husband, "HUSBAND");

        relations.put(brotherInLaw, "BROTHERINLAW");
        relations.put(sisterInLaw, "SISTERINLAW");


        relations.put(son, "SON");
        relations.put(daughter, "DAUGHTER");


        //Initialize some people
        shekhar.addRelative("SHEKHAR", me);
        shekhar.addRelative("ANJALI", wife);
        shekhar.addRelative("RAMESH", brother);
        shekhar.addRelative("SHRAVAN", brother);
        shekhar.addRelative("SUJEET", brother);
        shekhar.addRelative("OP GUPTA", father);
        shekhar.addRelative("ROHIT", brotherInLaw);
        shekhar.addRelative("NEHA", sisterInLaw);
        shekhar.addRelative("SAMRIDDHI", daughter);
        shekhar.addRelative("AMRITA", sisterNonSibling);
        shekhar.addRelative("SHWETA", sister);
        shekhar.addRelative("KSHITIJ", brotherInLaw);
        shekhar.addRelative("SANJEET", brotherNonSibling);
        shekhar.addRelative("RANJEET", brother);
        shekhar.addRelative("PRAVEEN KUMAR", fatherInLaw);
        shekhar.addRelative("NILAM DEVI", motherInLaw);


        people.put(shekhar.getName(), shekhar);
    }


    public int levelOfGeneration(Relation personOneRelation, Relation linkedPersonRelation){
        return personOneRelation.getY() + linkedPersonRelation.getY();
    }


    public boolean isInLaw(String personOne, String personSomeone, Person linkedPerson){
        Relation personOneRelation = linkedPerson.getRelativeByName(personOne).getRelation();
        Relation personSomeoneRelation = linkedPerson.getRelativeByName(personSomeone).getRelation();

        //Identify if relation between two people is in-law or not;
        //boolean inLaw = (personOneRelation.isInlaw() && personSomeoneRelation.isInlaw()) ? false : true;


        // If PersonOne is WIFE or HUSBAND and the PersonSomeone Relation with LinkedPerson is not In-Law
        // then the relationship between PersonOne and PersonSomeone is In-Law.
        if((personOneRelation.getX() == 0 && personOneRelation.getY() == 0) && !personSomeoneRelation.isInlaw()){
            return true;
        }


        if((personOneRelation.getX() == 0 && personOneRelation.getY() == 0) && personSomeoneRelation.getY() != 0){
            return !personSomeoneRelation.isInlaw();
        }

        // If PersonOne and PersonSomeone both has relationship with LinkedPerson as In-Law
        // then the relationship between PersonOne and PersonSomeone is not as In-Law
        if(personOneRelation.isInlaw() && personSomeoneRelation.isInlaw()){
            return false;
        }


        // If PersonOne and PersonSomeone both has not relationship with LinkedPerson as In-Law
        // then the relationship between PersonOne and PersonSomeone is not as In-Law
        if(!personOneRelation.isInlaw() && !personSomeoneRelation.isInlaw()){
            return false;
        }


        // If PersonOne or PersonSomeone any one has relationship with LinkedPerson as In-Law
        // then the relationship between PersonOne and PersonSomeone is as In-Law
        if(personOneRelation.isInlaw() || personSomeoneRelation.isInlaw()){
            return true;
        }

        else {
            return false;
        }
    }


    public boolean isSibling(String personOne, String personSomeone, Person linkedPerson){
        Relation personOneRelation = linkedPerson.getRelativeByName(personOne).getRelation();
        Relation personSomeoneRelation = linkedPerson.getRelativeByName(personSomeone).getRelation();
        Relation linkedPersonRelation = linkedPerson.getRelation();

        if(personOneRelation.isSibling() && personSomeoneRelation.isSibling()){
            return true;
        }
        else {

            return false;
        }
    }


    public Relation find(String personOne, String personSomeone, Person linkedPerson){
        Relation personOneRelation = linkedPerson.getRelativeByName(personOne).getRelation();
        Relation personSomeoneRelation = linkedPerson.getRelativeByName(personSomeone).getRelation();

        int x = 0;
        int y = 0;


        //Finding and making positions for the new relationship
        x = personOneRelation.getX() + personSomeoneRelation.getX();
        y = personOneRelation.getY() + personSomeoneRelation.getY();

        // If PersonOne is a sibling of LinkedPerson then the
        // Parent Relationship (Position) with the PersonOne would be same as between LinkedPerson.

        if (personOneRelation.isSibling && personSomeoneRelation.getY() > 0){
            x = linkedPerson.getRelation().getX();
        }


        //Any relationship in same generation will fall in Brothers and Sisters as default or In-Law.
        if(x > 1){
            x = 1; //x = 1 means brother or sister or In-Laws.
        }

        // We will not support relationship above than GREAT GRAND PARENT. Anyone above that will be called as
        // GREAT GRAND PARENT
        // Y = 1 Father
        // Y = 2 Grand Parent
        // Y = 3 Great Grand Parent
        if(y > 3){
            y = 3;
        }

        boolean inLaw = isInLaw(personOne, personSomeone, linkedPerson);
        //If PersonOne has sibling relationship with LinkedPerson then the sibling relationship with
        //SomeonePerson would be same as with LinkedPerson.
        boolean isSibling = isSibling(personOne, personSomeone, linkedPerson);
        Relation result = new Relation(x,y, personSomeoneRelation.getSex(), personSomeoneRelation.isOwner(), inLaw, isSibling);

        //If the resulting relation is not making any sense then
        // change its ownership (switch the Husband/Wife property) and then try to match
        if(!relations.containsKey(result)){
            result.setOwner(!personSomeoneRelation.isOwner());
        }

        System.out.println(personOne + "\t" + personOneRelation + "\t" + relations.get(personOneRelation));
        System.out.println(personSomeone + "\t" + personSomeoneRelation + "\t" + relations.get(personSomeoneRelation));
        System.out.println("RESULT" + "\t" + result + "\t" + relations.get(result));

        System.out.println("=============================================");
        return result;
    }


    public void showPeople(){
        people.forEach((name, person) -> {
            System.out.println(name + "\t" + relations.get(person.relation));
            System.out.println("=================================================");
            person.getRelatives()
                    .forEach((relativeName, relativePerson) -> {
                        System.out.println(relativeName + "\t" + relations.get(relativePerson.getRelation()));
                    });
        });
    }

    public static void main(String args[]){
        App findRelation = new App();
        findRelation.init();
        //findRelation.showPeople();

        /*
        findRelation.find("SHRAVAN", "ANJALI", shekhar);
        findRelation.find("ANJALI", "SHRAVAN", shekhar);
        findRelation.find("NEHA", "SHWETA", shekhar);
        */

        System.out.println(shekhar.getName() + "\t" + shekhar.getRelation());
        System.out.println("=============================================");


        //findRelation.find("ANJALI", "PRAVEEN KUMAR", shekhar);
        //findRelation.find("ANJALI", "OP GUPTA", shekhar);
        //findRelation.find("SHEKHAR", "PRAVEEN KUMAR", shekhar);
        //findRelation.find("SHEKHAR", "OP GUPTA", shekhar);

        findRelation.find("NEHA", "ANJALI", shekhar);
        findRelation.find("NEHA", "ROHIT", shekhar);
        findRelation.find("NEHA", "SHRAVAN", shekhar);
    }





    private class Person {

        private String name;
        private Relation relation;
        private Map<String, Person> relatives;

        public Person(String name, int sex){
            this.name = name;
            this.relatives = new HashMap<>();
            this.relation = new Relation(0, 0, sex, true, false, true);
        }

        public void addRelative(String name, Relation relationWithPerson){
            Person relative = new Person(name, relation.sex);
            relative.setRelation(relationWithPerson);
            relatives.put(name, relative);
        }

        public Map<String, Person> getRelatives(){
            return this.relatives;
        }

        public Person getRelativeByName(String name){
            return this.relatives.get(name);
        }


        public String getName(){
            return this.name;
        }

        public void setRelation(Relation relation){
            this.relation = relation;
        }

        public Relation getRelation(){
            return this.relation;
        }

    }

    private class Relation {

        int x;
        int y;
        int sex;
        /**
         * isOwner property helps to decide close In-Law relationship like:
         * Difference between Brother and SisterHusband and BrothersWife
         *
         * Relation brother = new Relation(1, 0, 1, true, false);
         * Relation sistersHus = new Relation(1, 0, 1, false, true);
         * Relation brotherWife = new Relation(1, 0, 0, false, true);
         *
         */
        boolean isOwner;
        boolean isInlaw;
        boolean isSibling;

        public Relation(int x, int y, int sex, boolean isOwner, boolean isInlaw, boolean isSibling){
            this.x = x;
            this.y = y;
            this.sex = sex;
            this.isOwner = isOwner;
            this.isInlaw = isInlaw;
            this.isSibling = isSibling;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public boolean isOwner() {
            return isOwner;
        }

        public void setOwner(boolean owner) {
            isOwner = owner;
        }

        public boolean isInlaw() {
            return isInlaw;
        }

        public void setInlaw(boolean inlaw) {
            isInlaw = inlaw;
        }

        public boolean isSibling() {
            return isSibling;
        }

        public void setSibling(boolean sibling) {
            isSibling = sibling;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Relation relation = (Relation) o;
            return x == relation.x &&
                    y == relation.y &&
                    sex == relation.sex &&
                    isOwner == relation.isOwner &&
                    isInlaw == relation.isInlaw &&
                    isSibling == relation.isSibling;
        }

        @Override
        public int hashCode() {

            return Objects.hash(x, y, sex, isOwner, isInlaw, isSibling);
        }

        @Override
        public String toString() {
            return "Relation{" +
                    "x=" + x +
                    ", y=" + y +
                    ", sex=" + sex +
                    ", isOwner=" + isOwner +
                    ", isInlaw=" + isInlaw +
                    ", isSibling=" + isSibling +
                    '}';
        }
    }

}

