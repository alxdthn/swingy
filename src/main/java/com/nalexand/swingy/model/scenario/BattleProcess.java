package com.nalexand.swingy.model.scenario;

import com.nalexand.swingy.controller.BattleWinController;
import com.nalexand.swingy.controller.DialogController;
import com.nalexand.swingy.model.Battle;
import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.model.ModelFacade;

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
            hero.hitPoints -= step.heroDamage;

            step.mobHp = mobHp;
            step.heroHp = heroHp;

            battle.addStep(step);
        }
        battle.isHeroWinner = heroHp > 0;
        if (battle.isHeroWinner) {
            battle.status = Battle.Status.WIN;
            model.increaseExperience(10000);
            model.moveHeroToMob();
        }
    }

    public static class Confirmation extends BaseScenarioStep implements DialogController {

        protected Confirmation(ModelFacade model) {
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
            model.clearBattle();
            model.nextStep(new GameProcess(model));
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
