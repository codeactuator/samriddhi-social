package com.codeactuator.samriddhi.dto;

import com.codeactuator.samriddhi.domain.Relation;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class RelationDTO implements Marshallable<Relation, RelationDTO> {


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

    public RelationDTO(){}

    public RelationDTO(int x, int y, int sex, boolean isOwner, boolean isInlaw, boolean isSibling){
        this.x = x;
        this.y = y;
        this.sex = sex;
        this.isOwner = isOwner;
        this.isInlaw = isInlaw;
        this.isSibling = isSibling;
    }

    public RelationDTO(int x, int y, int sex, boolean isOwner, boolean isInlaw, boolean isSibling, String name){
        this(x, y, sex, isOwner, isInlaw, isSibling);
        this.name = name;
    }


    @Override
    public Relation marshall() {
        Relation relation = new Relation();
        relation.setId(this.getId());
        relation.setName(this.getName());
        relation.setX(this.getX());
        relation.setY(this.getY());
        relation.setSibling(this.isSibling());
        relation.setOwner(this.isOwner());
        relation.setInlaw(this.isInlaw());
        relation.setSex(this.getSex());
        return relation;
    }

    @Override
    public void unmarshall(Relation relation) {
        this.setId(relation.getId());
        this.setName(relation.getName());
        this.setX(relation.getX());
        this.setY(relation.getY());
        this.setSibling(relation.isSibling());
        this.setOwner(relation.isOwner());
        this.setInlaw(relation.isInlaw());
        this.setSex(relation.getSex());
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
        RelationDTO that = (RelationDTO) o;
        return id == that.id &&
                x == that.x &&
                y == that.y &&
                sex == that.sex &&
                isOwner == that.isOwner &&
                isInlaw == that.isInlaw &&
                isSibling == that.isSibling;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, x, y, sex, isOwner, isInlaw, isSibling);
    }

    @Override
    public String toString() {
        return "RelationDTO{" +
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
