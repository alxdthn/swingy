package com.nalexand.swingy.ui.base;

import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.scenario.BaseScenarioStep;

public interface View {

    void renderScenarioData(BaseScenarioStep scenarioStep);

    void start(ModelFacade model);

    void stop();

    String getName();
}
