package com.nalexand.swingy.ui.console;

import com.nalexand.swingy.model.scenario.ModelFacade;
import com.nalexand.swingy.ui.base.BaseUserInterface;

public final class ConsoleUserInterface extends BaseUserInterface<ConsoleController, ConsoleView> {

    public ConsoleUserInterface(ModelFacade model) {
        super(new ConsoleController(model), new ConsoleView());
    }
}
