package com.nalexand.swingy.model.scenario;

import com.nalexand.swingy.model.base.BaseScenarioStep;
import com.nalexand.swingy.model.base.ScenarioStep;
import com.nalexand.swingy.ui.Command;

public class Dialog extends BaseScenarioStep<Welcome> implements ScenarioStep.Data {

    protected Dialog(ModelFacade model) {
        super(model);
    }

    @Override
    public void resolve(Command command) {

    }
}
