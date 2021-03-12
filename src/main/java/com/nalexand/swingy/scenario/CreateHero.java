package com.nalexand.swingy.scenario;

import com.nalexand.swingy.controller.Command;
import com.nalexand.swingy.model.Model;
import com.nalexand.swingy.view.View;

public class CreateHero implements Step {

    private final Model model;

    public CreateHero(Model model) {
        this.model = model;
    }

    @Override
    public void render(View view) {
        view.showCreateHero();
    }

    @Override
    public void resolve(Command command) {
        switch (command) {
            case OPTION_1:

        }
    }
}
