package com.nalexand.swingy.ui.base;

import com.nalexand.swingy.model.scenario.ModelFacade;

public abstract class BaseUserInterface<C extends Controller, V extends View> {

    private final C controller;

    private final V view;

    public BaseUserInterface(C controller, V view) {
        this.controller = controller;
        this.view = view;
    }

    public void start(ModelFacade model) {
        model.setView(view);
        controller.start();
    }
}
