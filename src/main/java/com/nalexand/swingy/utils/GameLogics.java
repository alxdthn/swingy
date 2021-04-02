package com.nalexand.swingy.utils;

import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.model.items.Item;
import com.nalexand.swingy.model.items.ItemFactory;

public class GameLogics {

    private static final double OBSTACLES_PERCENTAGE = 0.25;

    private static final int ITEM_GENERATION_PERCENTAGE = 50;

    private static final int BASE_DEFENCE_REDUCE_CHANCE_PERCENTAGE = 50;

    private static final int DEFENCE_REDUCE_CHANCE_PER_LEVEL_CHANGE_PERCENTAGE = 25;

    private static final int INITIAL_HERO_HP = 4;

    private GameLogics() {}

    public static int calculateXP() {
        return Utils.randomBetween(500, 1500);
    }

    public static int calculateMapSize(int heroLevel) {
        return ((heroLevel - 1) * 5) + 10 - (heroLevel % 2);
    }

    public static int calculateNumberOfObstacles(int size) {
        return (int) (size * size * OBSTACLES_PERCENTAGE);
    }

    public static int calculateLevelThreshold(int level) {
        return (level + 1) * 1000 + level * level * 450;
    }

    public static DamageResult calculateDamage(Hero dealer, Hero recipient) {
        int defenceReduceChance = BASE_DEFENCE_REDUCE_CHANCE_PERCENTAGE +
                (recipient.level - dealer.level) * DEFENCE_REDUCE_CHANCE_PER_LEVEL_CHANGE_PERCENTAGE;
        int defenceReduceChanceClamp = Math.min(Math.max(defenceReduceChance, 0), 100);
        int defenceReduce = (Utils.randomByPercent(defenceReduceChanceClamp)) ? recipient.getDefence() : 0;
        return new DamageResult(
                Math.max(
                        0,
                        dealer.getAttack() - defenceReduce
                ),
                defenceReduce
        );
    }

    public static void increaseXP(Hero hero, int xp) {
        while (xp > 0) {
            int toNextLevel = hero.levelThreshold - hero.xp;
            if (xp >= toNextLevel) {
                hero.levelUp();
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
        hero.hitPoints = INITIAL_HERO_HP;
        hero.currentHitPoints = hero.hitPoints;
    }

    public static void initAsMob(Hero mob, Hero hero) {
        mob.level = Math.max(1, hero.level + Utils.randomBetween(-1, 2));
        mob.attack = Math.max(1, hero.attack + Utils.randomBetween(-2, 3));
        mob.defence = Math.max(0, hero.defence + Utils.randomBetween(-1, 2));
        mob.hitPoints = Math.max(2, hero.hitPoints + Utils.randomBetween(-2, 3));
        if (Utils.randomByPercent(ITEM_GENERATION_PERCENTAGE)) {
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
    }

    public static class DamageResult {

        public int damage;

        public int blocked;

        public DamageResult(int damage, int blocked) {
            this.damage = damage;
            this.blocked = blocked;
        }
    }
}
