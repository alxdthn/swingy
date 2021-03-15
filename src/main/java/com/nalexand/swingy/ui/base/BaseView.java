package com.nalexand.swingy.ui.base;

import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.base.BaseScenarioStep;
import com.nalexand.swingy.model.scenario.*;

public abstract class BaseView implements View {

    protected abstract void showWelcome(ModelFacade model);

    protected abstract void showCreateHero(ModelFacade model);

    protected abstract void showGameProcess(ModelFacade model);

    protected abstract void showBattleConfirmation(ModelFacade model);

    protected abstract void showBattle(ModelFacade model);

    protected abstract void showBattleWin(ModelFacade model);

    protected abstract void showBattleLose(ModelFacade model);

    @Override
    final public void renderScenarioData(BaseScenarioStep scenarioStep) {
        ModelFacade model = scenarioStep.model;
        if (scenarioStep instanceof Welcome) {
            showWelcome(model);
        } else if (scenarioStep instanceof CreateHero) {
            showCreateHero(model);
        } else if (scenarioStep instanceof GameProcess) {
            showGameProcess(model);
        } else if (scenarioStep instanceof BattleProcess.Confirmation) {
            showBattleConfirmation(model);
        } else if (scenarioStep instanceof BattleProcess) {
            showBattle(model);
        } else if (scenarioStep instanceof BattleProcess.Win) {
            showBattleWin(model);
        } else if (scenarioStep instanceof BattleProcess.Lose) {
            showBattleLose(model);
        } else {
            throw new IllegalStateException("Unhandled scenario data " + scenarioStep.getClass().getSimpleName());
        }
    }
}
