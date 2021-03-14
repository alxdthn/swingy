package nalexand.swingy.ui;

import nalexand.swingy.model.Model;

public abstract class UserInterface<C extends Controller, V extends View> {

    private final C controller;

    private final V view;

    public UserInterface(C controller, V view) {
        this.controller = controller;
        this.view = view;
    }

    public void start(Model model) {
        model.setView(view);
        controller.start();
    }
}
