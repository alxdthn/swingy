package com.nalexand.swingy.utils;

import com.nalexand.swingy.Swingy;
import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.model.items.Item;
import com.nalexand.swingy.model.items.ItemFactory;

public class GameLogics {

    private GameLogics() {}

    public static int calculateXP() {
        return Utils.randomBetween(500, 1500);
    }

    public static void increaseXP(Hero hero, int xp) {
        while (xp > 0) {
            int levelThreshold = (hero.level + 1) * 1000 + hero.level * hero.level * 450;
            int toNextLevel = levelThreshold - hero.getXp();
            if (xp >= toNextLevel) {
                hero.level++;
                if (hero.level % 3 == 0) {
                    hero.hitPoints++;
                } else if (hero.level % 2 == 0) {
                    hero.attack++;
                } else {
                    hero.defence++;
                }
                hero.currentHitPoints = hero.getMaxHitPoints();
                hero.xp += toNextLevel;
                xp -= toNextLevel;
            } else {
                hero.xp += xp;
                return;
            }
        }
    }

    public static void initAsHero(Hero hero) {
        hero.created = true;
        hero.hitPoints = Swingy.INITIAL_HERO_HP;
        hero.currentHitPoints = hero.hitPoints;
    }

    public static void initAsMob(Hero mob, Hero hero) {
        mob.level = Math.max(1, hero.level + Utils.randomBetween(-1, 1));
        mob.attack = Math.max(1, hero.attack + Utils.randomBetween(-2, 2));
        mob.defence = Math.max(0, hero.defence + Utils.randomBetween(-1, 1));
        mob.hitPoints = Math.max(2, hero.hitPoints + Utils.randomBetween(-3, 3));
        if (Utils.randomByPercent(Swingy.ITEM_GENERATION_PERCENTAGE)) {
            Item randomItem = ItemFactory.randomItem();
            switch (randomItem.type) {
                case WEAPON:
                    mob.weapon = randomItem;
                    break;
                case ARMOR:
                    mob.armor = randomItem;
                    break;
                case HELMET:
                    mob.helmet = randomItem;
                    break;
            }
        }
        mob.currentHitPoints = mob.getMaxHitPoints();
//        mob.hitPoints = 0;
//        mob.currentHitPoints = 0;
    }
}
