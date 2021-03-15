package com.nalexand.swingy.model;

public class Hero {

    public String name;

    public Type type;

    public boolean created = false;

    public WorldMap worldMap = null;

    public int level = 0;

    private int xp = 0;

    private int hp = 0;

    private int attack = 0;

    private int defence = 0;

    private int hitPoints = 0;

    public int posX = 0;

    public int posY = 0;

    public Hero(Type type) {
        this.type = type;
        switch (type) {
            case VOID:
                this.name = "Void";
                break;
            case NEVERMORE:
                this.name = "Nevermore";
                break;
            case TRAXES:
                this.name = "Traxes";
                break;
            case URSA:
                this.name = "Ursa";
                break;
            case MOB:
                this.name = "Mob";
        }
    }

    public void increaseExperience(int xp) {
        while (xp > 0) {
            int levelThreshold = (level + 1) * 1000 + level * level * 450;
            int toNextLevel = levelThreshold - this.xp;
            if (xp > toNextLevel) {
                level++;
                this.xp += toNextLevel;
                xp -= toNextLevel;
            } else {
                this.xp += xp;
                xp = 0;
            }
        }
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public enum Type {
        VOID,
        NEVERMORE,
        TRAXES,
        URSA,
        MOB
    }
}
