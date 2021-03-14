package com.nalexand.swingy.ui.base;

import com.nalexand.swingy.model.scenario.CreateHero;
import com.nalexand.swingy.model.base.ScenarioStep;
import com.nalexand.swingy.model.scenario.GameProcess;
import com.nalexand.swingy.model.scenario.Welcome;

public abstract class BaseView implements View {

    protected abstract void showWelcome(Welcome welcome);

    protected abstract void showCreateHero(CreateHero createHero);

    protected abstract void showGameProcess(GameProcess gameProcess);

    @Override
    final public void renderScenarioData(ScenarioStep.Data scenarioData) {
        if (scenarioData instanceof Welcome) {
            showWelcome((Welcome) scenarioData);
        } else if (scenarioData instanceof CreateHero) {
            showCreateHero((CreateHero) scenarioData);
        } else if (scenarioData instanceof GameProcess) {
            showGameProcess((GameProcess) scenarioData);
        } else {
            throw new IllegalStateException("Unhandled scenario data " + scenarioData.getClass().getSimpleName());
        }
    }
}
