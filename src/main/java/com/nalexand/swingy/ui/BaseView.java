package com.nalexand.swingy.ui;

import com.nalexand.swingy.controller.*;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.scenario.*;

public abstract class BaseView implements View {

    protected abstract void showWelcome(ModelFacade model, WelcomeController controller);

    protected abstract void showCreateHero(ModelFacade model, CreateHeroController controller);

    protected abstract void showGameProcess(ModelFacade model, GameProcessController controller);

    protected abstract void showBattleConfirmation(ModelFacade model, DialogController controller);

    protected abstract void showBattle(ModelFacade model, BattleController controller);

    protected abstract void showBattleWin(ModelFacade model, BattleWinController controller);

    protected abstract void showBattleLose(ModelFacade model, DialogController controller);

    @Override
    public void renderScenarioData(BaseScenarioStep scenarioStep) {
        ModelFacade model = scenarioStep.model;
        if (scenarioStep instanceof Welcome) {
            showWelcome(model, (WelcomeController) scenarioStep);
        } else if (scenarioStep instanceof CreateHero) {
            showCreateHero(model, (CreateHeroController) scenarioStep);
        } else if (scenarioStep instanceof GameProcess) {
            showGameProcess(model, (GameProcessController) scenarioStep);
        } else if (scenarioStep instanceof BattleProcess.Confirmation) {
            showBattleConfirmation(model, (DialogController) scenarioStep);
        } else if (scenarioStep instanceof BattleProcess) {
            showBattle(model, (BattleController) scenarioStep);
        } else if (scenarioStep instanceof BattleProcess.Win) {
            showBattleWin(model, (BattleWinController) scenarioStep);
        } else if (scenarioStep instanceof BattleProcess.Lose) {
            showBattleLose(model, (DialogController) scenarioStep);
        } else {
            throw new IllegalStateException("Unhandled scenario data " + scenarioStep.getClass().getSimpleName());
        }
    }
}
