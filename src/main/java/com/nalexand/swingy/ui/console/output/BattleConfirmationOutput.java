package com.nalexand.swingy.ui.console.output;

import com.nalexand.swingy.controller.DialogController;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.models.Battle;
import com.nalexand.swingy.model.models.Hero;
import com.nalexand.swingy.ui.console.BaseConsoleOutput;
import com.nalexand.swingy.ui.console.Command;

import java.util.Arrays;

import static com.nalexand.swingy.ui.console.output.OutputUtils.printDash;
import static com.nalexand.swingy.utils.Utils.*;

public class BattleConfirmationOutput extends BaseConsoleOutput {

    public BattleConfirmationOutput(ModelFacade model, DialogController controller) {
        printDash();

        Hero hero = model.getSelectedHero();
        Battle battle = model.getBattle();
        Hero mob = battle.mob;

        printFormat("You are meet with %s\n", mob.name);

        printTableLine("name", "level", "hp", "attack", "defence");
        printStatLine(hero);
        printStatLine(mob);

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

    private void printTableLine(Object... args) {
        Object[] nextArgs = Arrays.stream(args).toArray();
        printFormat("%-12s|%-12s|%-12s|%-12s|%-12s\n", nextArgs);
    }

    private void printStatLine(Hero hero) {
        printTableLine(
                hero.name,
                hero.level,
                String.format("%d/%d", hero.currentHitPoints, hero.getMaxHitPoints()),
                hero.getAttack(),
                hero.getDefence()
        );
    }
}
