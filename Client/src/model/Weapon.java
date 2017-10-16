package model;

import java.sql.Blob;

public class Weapon {
    private String name;
    private int type;
    private int weight;
    private int value;
    private Blob icon;
    private int damage;
    private double critchance;
    private int attackspeed;
    private int level;
    private int weapon_id;

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

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setCritchance(double critchance) {
        this.critchance = critchance;
    }

    public double getCritchance() {
        return critchance;
    }

    public void setAttackspeed(int attackspeed) {
        this.attackspeed = attackspeed;
    }

    public int getAttackspeed() {
        return attackspeed;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setWeapon_id(int weapon_id) {
        this.weapon_id = weapon_id;
    }

    public int getWeapon_id() {
        return weapon_id;
    }
}
