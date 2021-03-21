package com.nalexand.swingy.ui.console.output;

import com.nalexand.swingy.controller.DialogController;
import com.nalexand.swingy.model.Battle;
import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.ui.base.BaseConsoleOutput;
import com.nalexand.swingy.ui.console.Command;

import static com.nalexand.swingy.ui.console.output.OutputUtils.printDash;
import static com.nalexand.swingy.utils.Utils.*;

public class BattleConfirmationOutput extends BaseConsoleOutput {

    public BattleConfirmationOutput(ModelFacade model, DialogController controller) {
        printDash();

        Battle battle = model.getBattle();
        Hero mob = battle.mob;
        printFormat("You are meet with %s\n", mob.name);
        printFormat("level %s\n", mob.level);
        println("Items:");
        listOfNotNull(
                mob.helmet,
                mob.armor,
                mob.weapon
        ).forEach(item -> println(item.getFormattedString()));

        println("1: Fight");
        println("2: Run");

        listenCommand(Command.KEY_1, controller::accept);
        listenCommand(Command.KEY_2, controller::dismiss);
    }
}
