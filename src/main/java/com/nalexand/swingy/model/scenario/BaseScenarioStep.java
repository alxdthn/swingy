package com.nalexand.swingy.model.scenario;

import com.nalexand.swingy.model.ModelFacade;

public abstract class BaseScenarioStep {

    public ModelFacade model;

    public void onRendered() { /* do nothing */ }

    protected BaseScenarioStep(ModelFacade model) {
        this.model = model;
    }
}
