package com.nalexand.swingy.ui.console.output;

import com.nalexand.swingy.controller.BattleWinController;
import com.nalexand.swingy.model.Battle;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.items.Item;
import com.nalexand.swingy.ui.base.BaseConsoleOutput;
import com.nalexand.swingy.ui.console.Command;

import java.util.List;

import static com.nalexand.swingy.ui.console.output.OutputUtils.printDash;
import static com.nalexand.swingy.utils.Utils.*;

public class BattleWinOutput extends BaseConsoleOutput {

    public BattleWinOutput(ModelFacade model, BattleWinController controller) {
        printDash();

        Battle battle = model.getBattle();

        println("You are win!");
        List<Item> drop = listOfNotNull(
                battle.mob.helmet,
                battle.mob.armor,
                battle.mob.weapon
        );
        if (!drop.isEmpty()) {
            println("Drop:");
            int option = 1;
            for (Item item : drop) {
                printFormat("%d: %s", option++, item.getFormattedString());
            }
        }
        println("1: Ok!");
        listenCommand(Command.KEY_1, controller::accept);
    }
}
