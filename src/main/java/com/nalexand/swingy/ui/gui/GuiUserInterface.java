package com.nalexand.swingy.ui.gui;

import com.nalexand.swingy.model.scenario.ModelFacade;
import com.nalexand.swingy.ui.base.BaseUserInterface;

public final class GuiUserInterface extends BaseUserInterface<GuiController, GuiView> {

    public GuiUserInterface(ModelFacade model, GuiView guiView) {
        super(new GuiController(model, guiView), guiView);
    }
}