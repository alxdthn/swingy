package com.nalexand.swingy.scenario;

import com.nalexand.swingy.controller.Command;
import com.nalexand.swingy.model.Model;
import com.nalexand.swingy.view.View;

public interface Step {

    void render(View view);

    void resolve(Command command);
}
