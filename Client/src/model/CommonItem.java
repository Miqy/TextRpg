package model;

import java.sql.Blob;

public class CommonItem {
    private String name;
    private int type;
    private int weight;
    private int value;
    private Blob icon;
    private int power;
    private int commonitem_id;

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

    public void setPower(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public void setCommonitem_id(int commonitem_id) {
        this.commonitem_id = commonitem_id;
    }

    public int getCommonitem_id() {
        return commonitem_id;
    }
}
