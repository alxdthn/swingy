package com.nalexand.swingy.model.base;

import com.nalexand.swingy.model.scenario.ModelFacade;

public abstract class BaseScenarioStep<Child extends ScenarioStep.Data> implements ScenarioStep<Child> {

    protected ModelFacade model;

    protected BaseScenarioStep(ModelFacade model) {
        this.model = model;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Child provideScenarioData() {
        return (Child) this;
    }
}
