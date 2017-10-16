package model;

public class Enchant {
    private int enchant_id;
    private String name;
    private int type;
    private int power;

    public void setEnchant_id(int enchant_id) {
        this.enchant_id = enchant_id;
    }

    public int getEnchant_id() {
        return enchant_id;
    }

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

    public void setPower(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }
}
