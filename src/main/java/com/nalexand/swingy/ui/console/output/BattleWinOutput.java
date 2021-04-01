package com.nalexand.swingy.ui.console.output;

import com.nalexand.swingy.controller.BattleWinController;
import com.nalexand.swingy.model.Battle;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.items.Item;
import com.nalexand.swingy.ui.console.BaseConsoleOutput;
import com.nalexand.swingy.ui.console.Command;

import java.util.List;

import static com.nalexand.swingy.ui.console.output.OutputUtils.printDash;
import static com.nalexand.swingy.utils.Utils.*;

public class BattleWinOutput extends BaseConsoleOutput {

    public BattleWinOutput(ModelFacade model, BattleWinController controller) {
        Battle battle = model.getBattle();
        switch (battle.status) {
            case WIN:
                printWinState(controller, battle);
                break;
            case LOOT:
                printLootState(controller, battle);
                break;
            default:
                throw new IllegalStateException("Bad status: " + battle.status.toString());
        }
    }

    private void printWinState(BattleWinController controller, Battle battle) {
        printState(
                String.format("Win!\nXP = %d\n", battle.xp),
                controller,
                battle
        );
    }

    private void printLootState(BattleWinController controller, Battle battle) {
        printState("You are find the loot!", controller, battle);
    }

    private void printState(String title, BattleWinController controller, Battle battle) {
        printDash();

        println(title);

        Command[] commands = {Command.KEY_1, Command.KEY_2, Command.KEY_3, Command.KEY_4};

        List<Item> loot = listOfNotNull(
                battle.mob.helmet,
                battle.mob.armor,
                battle.mob.weapon
        );
        int option = 1;
        if (!loot.isEmpty()) {
            println("Loot:");
            for (Item item : loot) {
                printFormat("%d: %s\n", option, item.getFormattedString());
                listenCommand(commands[option - 1], () -> controller.takeLootItem(item));
                option++;
            }
        }
        printFormat("%d: Close\n", option);
        listenCommand(commands[option - 1], controller::accept);
    }
}
