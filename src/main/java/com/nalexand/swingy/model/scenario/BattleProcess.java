package com.nalexand.swingy.model.scenario;

import com.nalexand.swingy.controller.BattleWinController;
import com.nalexand.swingy.controller.DialogController;
import com.nalexand.swingy.model.Battle;
import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.items.Item;

public class BattleProcess extends BaseScenarioStep {

    protected BattleProcess(ModelFacade model) {
        super(model);
        runBattle();
    }

    @Override
    public void onRendered() {
        super.onRendered();
        if (model.getBattle().isHeroWinner) {
            model.nextStep(new BattleProcess.Win(model));
        } else {
            model.nextStep(new BattleProcess.Lose(model));
        }
    }

    private void runBattle() {
        Battle battle = model.getBattle();

        Hero mob = battle.mob;
        Hero hero = model.getSelectedHero();

        int heroHp = hero.getHitPoints();
        int mobHp = mob.getHitPoints();

        while (heroHp > 0 && mobHp > 0) {
            Battle.Step step = new Battle.Step();
            step.mobDamage = Math.max(0, hero.getAttack() - mob.getDefence());
            step.heroDamage = Math.max(0, mob.getAttack() - hero.getDefence());

            heroHp -= step.heroDamage;
            mobHp -= step.mobDamage;
            hero.currentHitPoints -= step.heroDamage;

            step.mobHp = mobHp;
            step.heroHp = heroHp;

            battle.addStep(step);
        }
        battle.isHeroWinner = heroHp > 0;
        if (battle.isHeroWinner) {
            battle.status = Battle.Status.WIN;
            model.increaseExperience(500);
            hero.worldMap.removeMob(mob);
            model.moveHeroToMob();
        }
        model.saveGameState();
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
            } else {
                mob.dropItem(item);
            }
            model.saveGameState();
            model.render();
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
