package com.nalexand.swingy.ui.console.output;

import com.nalexand.swingy.controller.DialogController;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.ui.base.BaseConsoleOutput;
import com.nalexand.swingy.ui.console.Command;

import static com.nalexand.swingy.ui.console.output.OutputUtils.printDash;
import static com.nalexand.swingy.utils.Utils.println;

public class BattleLoseOutput extends BaseConsoleOutput {

    public BattleLoseOutput(ModelFacade model, DialogController controller) {
        printDash();

        println("You are lose :(");
        println("1: Fuck!");
        listenCommand(Command.KEY_1, controller::accept);
    }
}
