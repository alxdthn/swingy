package com.nalexand.swingy.model;

import com.nalexand.swingy.model.items.Item;
import com.nalexand.swingy.utils.GameLogics;

import java.util.ArrayList;
import java.util.List;

import static com.nalexand.swingy.utils.Utils.listOfNotNull;

public class Battle {

    public Hero mob;

    private List<Step> steps = new ArrayList<>();

    public Status status = Status.CONFIRMATION;

    public boolean isHeroWinner = false;

    public int heroStartHp;

    public int mobStartHp;

    public int posX;

    public int posY;

    public int xp = GameLogics.calculateXP();

    public Battle(Hero hero, Hero mob, int posX, int posY) {
        this.mob = mob;
        this.posX = posX;
        this.posY = posY;
        heroStartHp = hero.hitPoints;
        mobStartHp = mob.hitPoints;
    }

    public void addStep(Step step) {
        if (steps == null) {
            steps = new ArrayList<>();
        }
        steps.add(step);
        step.number = steps.size();
    }

    public List<Item> getLoot() {
        return listOfNotNull(mob.weapon, mob.armor, mob.helmet);
    }

    public List<Step> getSteps() {
        return new ArrayList<>(steps);
    }

    public enum Status { CONFIRMATION, WIN, LOOT }

    public static class Step {

        public int number = 0;

        public int heroHp = 0;

        public int mobHp = 0;

        public int heroDamage = 0;

        public int mobDamage = 0;

        public String format() {
            return String.format("%-8d|%8d|%8d -> | <- %-8d|%-8d", number, heroHp, mobDamage, heroDamage, mobHp);
        }
    }
}
