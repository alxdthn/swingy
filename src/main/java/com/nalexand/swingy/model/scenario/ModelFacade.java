package com.nalexand.swingy.model.scenario;

import com.nalexand.swingy.model.GameState;
import com.nalexand.swingy.model.base.ScenarioStep;
import com.nalexand.swingy.ui.Command;
import com.nalexand.swingy.ui.base.View;
import com.nalexand.swingy.utils.Utils;

public class ModelFacade {

    private View view = null;

    private GameState gameState = Utils.loadGameState();

    @SuppressWarnings("rawtypes")
    private ScenarioStep currentStep = new Welcome(this);

    public void render() {
        view.renderScenarioData(currentStep.provideScenarioData());
    }

    public void resolveCommand(Command command) {
        currentStep.resolve(command);
    }

    public void setView(View view) {
        this.view = view;
    }

    GameState getGameState() {
        return gameState;
    }

    @SuppressWarnings("rawtypes")
    void nextStep(ScenarioStep scenarioStep) {
        currentStep = scenarioStep;
        render();
    }
}
