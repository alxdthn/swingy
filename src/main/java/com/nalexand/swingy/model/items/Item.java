package com.nalexand.swingy.model.items;

public class Item {

    public Type type;

    public String name;

    public int attack;

    public int defence;

    public int hitPoints;

    public Item(Type type, String name, int attack, int defence, int hitPoints) {
        this.type = type;
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.hitPoints = hitPoints;
    }

    public String getFormattedString() {
        switch (type) {
            case WEAPON:
                return String.format("weapon: %s (attack = %d)", name, attack);
            case ARMOR:
                return String.format("armor %s (defence = %d)", name, defence);
            default:
                return String.format("helmet %s (hitPoints = %d)", name, hitPoints);
        }
    }

    public static String safeFormatItem(Item item) {
        if (item == null) {
            return "EMPTY";
        } else {
            return item.getFormattedString();
        }
    }

    public enum Type { WEAPON, ARMOR, HELMET }
}
