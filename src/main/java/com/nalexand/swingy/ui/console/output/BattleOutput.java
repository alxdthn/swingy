package com.nalexand.swingy.ui.console.output;

import com.nalexand.swingy.controller.BattleController;
import com.nalexand.swingy.model.Battle;
import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.ui.base.BaseConsoleOutput;
import com.nalexand.swingy.ui.console.Command;

import static com.nalexand.swingy.ui.console.output.OutputUtils.printDash;
import static com.nalexand.swingy.utils.Utils.printFormat;
import static com.nalexand.swingy.utils.Utils.println;

public class BattleOutput extends BaseConsoleOutput {

    public BattleOutput(ModelFacade model, BattleController controller) {
        printDash();

        Battle battle = model.getBattle();
        Hero hero = model.getSelectedHero();

        String heroTitle = String.format("%s (%d hp)", hero.name, battle.heroStartHp);
        String mobTitle = String.format("%s (%d hp)", battle.mob.name, battle.mobStartHp);

        printFormat("%34s\n", "BATTLE!");
        printFormat("%28s  |  %s\n", heroTitle, mobTitle);
        printFormat("%-8s|%8s|%12s|%-12s|%-8s\n", "step", "hp", "damage", "damage", "hp");
        battle.getSteps().forEach(step -> println(step.format()));
        println("1: Ok!");
        listenCommand(Command.KEY_1, controller::accept);
    }
}
