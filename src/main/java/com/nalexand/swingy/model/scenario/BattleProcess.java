package com.nalexand.swingy.model.scenario;

import com.nalexand.swingy.model.Battle;
import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.ui.Command;

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

    @Override
    public void resolve(Command command) {

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

    public static class Confirmation extends BaseScenarioStep {

        protected Confirmation(ModelFacade model) {
            super(model);
        }

        @Override
        public void resolve(Command command) {
            switch (command) {
                case KEY_1:
                    model.saveGameState();
                    model.nextStep(new BattleProcess(model));
                    break;
                case KEY_2:
                    model.clearBattle();
                    model.nextStep(new GameProcess(model));
                    break;
            }
        }
    }

    public static class Win extends BaseScenarioStep {

        protected Win(ModelFacade model) {
            super(model);
        }

        @Override
        public void resolve(Command command) {
            if (command == Command.KEY_1) {
                model.clearBattle();
                model.nextStep(new GameProcess(model));
            }
        }
    }

    public static class Lose extends BaseScenarioStep {

        protected Lose(ModelFacade model) {
            super(model);
            model.satisfyHero();
        }

        @Override
        public void resolve(Command command) {
            if (command == Command.KEY_1) {
                model.nextStep(new Welcome(model));
            }
        }
    }
}
