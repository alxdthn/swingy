package com.nalexand.swingy.model;

import com.nalexand.swingy.Swingy;
import com.nalexand.swingy.model.items.Item;
import com.nalexand.swingy.model.items.ItemFactory;
import com.nalexand.swingy.utils.Utils;

public class Hero {

    public String name;

    public Type type;

    public boolean created = false;

    public WorldMap worldMap = null;

    public Battle battle = null;

    public int level = 0;

    private int xp = 0;

    private int attack = 1;

    private int defence = 0;

    public int hitPoints = 0;

    public int currentHitPoints = 0;

    public int posX = 0;

    public int posY = 0;

    public Item weapon = null;

    public Item armor = null;

    public Item helmet = null;

    public Hero(Type type) {
        this.type = type;
        switch (type) {
            case VOID:
                this.name = "Void";
                break;
            case NEVERMORE:
                this.name = "Nevermore";
                break;
            case TRAXEX:
                this.name = "Traxex";
                break;
            case URSA:
                this.name = "Ursa";
                break;
            case MOB:
                this.name = Swingy.MOB_NAMES[Utils.randomBetween(0, Swingy.MOB_NAMES.length - 1)];
        }
    }

    public void increaseExperience(int xp) {
        while (xp > 0) {
            int levelThreshold = (level + 1) * 1000 + level * level * 450;
            int toNextLevel = levelThreshold - this.xp;
            if (xp >= toNextLevel) {
                level++;
                this.xp += toNextLevel;
                xp -= toNextLevel;
            } else {
                this.xp += xp;
                return;
            }
        }
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public int getAttack() {
        return attack + ((weapon != null) ? weapon.attack : 0);
    }

    public int getDefence() {
        return defence + ((armor != null) ? armor.defence : 0);
    }

    public int getHitPoints() {
        return hitPoints + ((helmet != null) ? helmet.hitPoints : 0);
    }

    public void initAsHero() {
        created = true;
        hitPoints = 4;
        currentHitPoints = 4;
    }

    public void initAsMob(Hero hero) {
        level = hero.level + 1;
        hitPoints = 3;
        if (Utils.randomByPercent(Swingy.ITEM_GENERATION_PERCENTAGE)) {
            Item randomItem = ItemFactory.randomItem();
            switch (randomItem.type) {
                case WEAPON:
                    weapon = randomItem;
                    break;
                case ARMOR:
                    armor = randomItem;
                    break;
                case HELMET:
                    helmet = randomItem;
                    break;
            }
        }
    }

    public Item takeItem(Item item) {
        Item returnItem = null;
        switch (item.type) {
            case WEAPON:
                returnItem = weapon;
                weapon = item;
                break;
            case ARMOR:
                returnItem = armor;
                armor = item;
                break;
            case HELMET:
                returnItem = helmet;
                helmet = item;
        }
        return returnItem;
    }

    public void dropItem(Item item) {
        switch (item.type) {
            case WEAPON:
                weapon = null;
                break;
            case ARMOR:
                armor = null;
                break;
            case HELMET:
                helmet = null;
                break;
        }
    }

    public enum Type {
        VOID,
        NEVERMORE,
        TRAXEX,
        URSA,
        MOB
    }
}
