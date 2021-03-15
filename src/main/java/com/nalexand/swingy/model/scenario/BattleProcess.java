package com.nalexand.swingy.model.scenario;

import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.base.BaseScenarioStep;
import com.nalexand.swingy.ui.Command;

public class BattleProcess extends BaseScenarioStep {

    protected BattleProcess(ModelFacade model) {
        super(model);
    }

    @Override
    public void resolve(Command command) {
        switch (command) {
            case KEY_1:
                model.nextStep(new BattleProcess.Win(model));
                break;
            case KEY_2:
                model.nextStep(new BattleProcess.Lose(model));
                break;
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
                    model.getBattle().isConfirmed = true;
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
            model.moveHeroToMob();
            model.removeBattleMob();
            model.clearBattle();
            model.increaseExperience(10000);
        }

        @Override
        public void resolve(Command command) {
            if (command == Command.KEY_1) {
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
