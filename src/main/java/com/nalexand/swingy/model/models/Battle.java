package com.nalexand.swingy.model.models;

import com.nalexand.swingy.model.GameLogics;
import com.nalexand.swingy.model.models.items.Item;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.nalexand.swingy.utils.Utils.listOfNotNull;

public class Battle implements Serializable {

    @Valid
    @NotNull
    public Hero mob;

    @Valid
    @NotNull
    private List<Step> steps = new ArrayList<>();

    @NotNull
    public Status status = Status.CONFIRMATION;

    public boolean isHeroWinner = false;

    @Min(0)
    public int heroStartHp;

    @Min(0)
    public int mobStartHp;

    @Min(0)
    public int posX;

    @Min(0)
    public int posY;

    @Min(0)
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
    }

    public List<Item> getLoot() {
        return listOfNotNull(mob.weapon, mob.armor, mob.helmet);
    }

    public List<Step> getSteps() {
        return new ArrayList<>(steps);
    }

    public enum Status { CONFIRMATION, WIN, LOOT }

    public static class Step implements Serializable {

        @NotNull
        public String dealerName = null;

        @NotNull
        public String recipient = null;

        @NotNull
        public String abstractRecipientIdentifier = null;

        public int recipientDamage = 0;

        public int recipientHpLeft = 0;

        public int recipientBlockedDamage = 0;

        public String getMessage() {
            return String.format(
                    "%s attack %s: %d damage, %s %d HP remaining, blocked %d",
                    dealerName,
                    recipient,
                    recipientDamage,
                    abstractRecipientIdentifier,
                    recipientHpLeft,
                    recipientBlockedDamage
            );
        }
    }
}
