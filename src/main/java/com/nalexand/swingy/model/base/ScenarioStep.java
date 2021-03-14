package com.nalexand.swingy.model.base;

import com.nalexand.swingy.ui.Command;

public interface ScenarioStep<ScenarioData extends ScenarioStep.Data> {

    void resolve(Command command);

    ScenarioData provideScenarioData();

    interface Data {}
}
