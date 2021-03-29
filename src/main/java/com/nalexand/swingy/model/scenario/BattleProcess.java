package com.nalexand.swingy.model.scenario;

import com.nalexand.swingy.controller.BattleController;
import com.nalexand.swingy.controller.BattleWinController;
import com.nalexand.swingy.controller.DialogController;
import com.nalexand.swingy.model.Battle;
import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.items.Item;
import com.nalexand.swingy.utils.GameLogics;

public class BattleProcess extends BaseScenarioStep implements BattleController {

    public BattleProcess(ModelFacade model) {
        super(model);
        runBattle();
    }

    private void runBattle() {
        Battle battle = model.getBattle();

        Hero mob = battle.mob;
        Hero hero = model.getSelectedHero();

        boolean isHeroTurn = true;
        while (hero.currentHitPoints > 0 && mob.currentHitPoints > 0) {
            Battle.Step step = new Battle.Step();

            if (isHeroTurn) {
                //  Mob get damage
                step.owner = hero.name;
                step.abstractRecipientIdentifier = "enemy";
                step.recipient = mob.name;
                step.recipientDamage = Math.max(0, hero.getAttack() - mob.getDefence());
                mob.currentHitPoints -= step.recipientDamage;
                step.recipientHpLeft = mob.currentHitPoints;
            } else {
                //  Hero get damage
                step.owner = mob.name;
                step.abstractRecipientIdentifier = "  you";
                step.recipient = hero.name;
                step.recipientDamage = Math.max(0, mob.getAttack() - hero.getDefence());
                hero.currentHitPoints -= step.recipientDamage;
                step.recipientHpLeft = hero.currentHitPoints;
            }
            isHeroTurn = !isHeroTurn;
            battle.addStep(step);
        }
        battle.isHeroWinner = hero.currentHitPoints > 0;
        if (battle.isHeroWinner) {
            battle.status = Battle.Status.WIN;
            GameLogics.increaseXP(hero, battle.xp);
            hero.worldMap.removeMob(mob);
            model.moveHeroToMob();
        }
        model.saveGameState();
    }

    @Override
    public void accept() {
        if (model.getBattle().isHeroWinner) {
            model.nextStep(new BattleProcess.Win(model));
        } else {
            model.nextStep(new BattleProcess.Lose(model));
        }
    }

    public static class Confirmation extends BaseScenarioStep implements DialogController {

        public Confirmation(ModelFacade model) {
            super(model);
        }

        @Override
        public void accept() {
            model.saveGameState();
            model.nextStep(new BattleProcess(model));
        }

        @Override
        public void dismiss() {
            model.clearBattle();
            model.nextStep(new GameProcess(model));
        }
    }

    public static class Win extends BaseScenarioStep implements BattleWinController {

        protected Win(ModelFacade model) {
            super(model);
        }

        @Override
        public void accept() {
            Battle battle = model.getBattle();
            if (battle.getLoot().isEmpty()) {
                model.getSelectedHero().worldMap.getCells().get(battle.posY).get(battle.posX).battle = null;
            } else {
                battle.status = Battle.Status.LOOT;
            }
            model.clearBattle();
            model.nextStep(new GameProcess(model));
        }

        @Override
        public void takeLootItem(Item item) {
            Hero hero = model.getSelectedHero();
            Hero mob = model.getBattle().mob;
            Item currentItem = hero.takeItem(item);
            if (currentItem != null) {
                mob.takeItem(currentItem);
                model.saveGameState();
                model.render();
            } else {
                mob.dropItem(item);
                accept();
            }
        }
    }

    public static class Lose extends BaseScenarioStep implements DialogController {

        protected Lose(ModelFacade model) {
            super(model);
            model.satisfyHero();
        }

        @Override
        public void accept() {
            model.nextStep(new Welcome(model));
        }

        @Override
        public void dismiss() {
            //  TODO
        }
    }
}
