package com.nalexand.swingy.ui.gui;

import com.nalexand.swingy.model.scenario.ModelFacade;
import com.nalexand.swingy.ui.Command;
import com.nalexand.swingy.ui.base.Controller;

public final class GuiController implements Controller {

    private final ModelFacade model;

    private final GuiView view;

    public GuiController(ModelFacade model, GuiView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void start() {
        view.initController(this);
        view.initGui();
        model.render();
    }

    @Override
    public void close() {

    }

    public void createNewHeroButton() {
        model.resolveCommand(Command.OPTION_1);
    }
}
