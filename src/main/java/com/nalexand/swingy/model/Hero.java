package com.nalexand.swingy.model;

import java.util.Objects;

public class Hero {

    public String name;

    public Type type;

    public boolean created = false;

    private WorldMap worldMap = new WorldMap();

    private int level = 1;

    private int xp = 0;

    private int hp = 0;

    private int attack = 0;

    private int defence = 0;

    private int hitPoints = 0;

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
        }
    }

    public void increaseExperience(int xp) {
        int additionalXp = this.xp + xp;
        int levelThreshold = level * 1000 + (level - 1) * (level - 1) * 450;
        if (additionalXp >= levelThreshold) {
            level++;
            this.xp += Math.min(additionalXp, levelThreshold);
            if (additionalXp - levelThreshold > 0) {
                increaseExperience(additionalXp - levelThreshold);
            }
        }
    }

    public void prepareToGame() {
        if (worldMap.getSize() == 0) {
            calculateWorldMap();
        }
    }

    public void calculateWorldMap() {
        worldMap.generateMap((level - 1) * 5 + 10);
    }

    public WorldMap getWorldMap() {
        return worldMap;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return Objects.equals(type, hero.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public enum Type {
        VOID,
        NEVERMORE,
        TRAXES,
        URSA
    }
}
