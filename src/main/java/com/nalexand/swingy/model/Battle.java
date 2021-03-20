package com.nalexand.swingy.model;

import java.util.ArrayList;
import java.util.List;

public class Battle {

    public Hero mob;

    private List<Step> steps = new ArrayList<>();

    public Status status = Status.CONFIRMATION;

    public boolean isHeroWinner = false;

    public int heroStartHp;

    public int mobStartHp;

    public Battle(Hero hero, Hero mob) {
        this.mob = mob;
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

    public List<Step> getSteps() {
        return new ArrayList<>(steps);
    }

    public enum Status { CONFIRMATION, WIN }

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
