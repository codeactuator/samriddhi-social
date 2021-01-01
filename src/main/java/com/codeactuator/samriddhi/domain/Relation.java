package com.codeactuator.samriddhi.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@XmlRootElement
public class Relation {

    @Id
    @GeneratedValue
    int id;
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
    private String name;

    public Relation(int x, int y, int sex, boolean isOwner, boolean isInlaw, boolean isSibling){
        this.x = x;
        this.y = y;
        this.sex = sex;
        this.isOwner = isOwner;
        this.isInlaw = isInlaw;
        this.isSibling = isSibling;
    }

    public Relation(int x, int y, int sex, boolean isOwner, boolean isInlaw, boolean isSibling, String name){
        this(x, y, sex, isOwner, isInlaw, isSibling);
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", sex=" + sex +
                ", isOwner=" + isOwner +
                ", isInlaw=" + isInlaw +
                ", isSibling=" + isSibling +
                ", name='" + name + '\'' +
                '}';
    }
}
