package model;

import java.sql.Blob;

public class Armor {
    private String name;
    private int type;
    private int weight;
    private int value;
    private Blob icon;
    private int defence;
    private int weightType;
    private int level;
    private int armor_id;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setIcon(Blob icon) {
        this.icon = icon;
    }

    public Blob getIcon() {
        return icon;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getDefence() {
        return defence;
    }

    public void setWeightType(int weightType) {
        this.weightType = weightType;
    }

    public int getWeightType() {
        return weightType;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setArmor_id(int armor_id) {
        this.armor_id = armor_id;
    }

    public int getArmor_id() {
        return armor_id;
    }
}
