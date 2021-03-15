package com.nalexand.swingy.model.base;

import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.ui.Command;

public abstract class BaseScenarioStep {

    public ModelFacade model;

    public void onRendered() { /* do nothing */ }

    public abstract void resolve(Command command);

    protected BaseScenarioStep(ModelFacade model) {
        this.model = model;
    }
}
