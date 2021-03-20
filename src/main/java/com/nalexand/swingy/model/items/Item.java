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

    public enum Type { WEAPON, ARMOR, HELMET }
}
