package com.nalexand.swingy.scenario;

import com.nalexand.swingy.controller.Command;
import com.nalexand.swingy.model.Model;
import com.nalexand.swingy.view.View;

public class Welcome implements Step {

    private final Model model;

    public Welcome(Model model) {
        this.model = model;
    }

    @Override
    public void render(View view) {
        view.showWelcome();
    }

    @Override
    public void resolve(Command command) {
        switch (command) {
            case OPTION_1:
                model.nextStep();
        }
    }
}
