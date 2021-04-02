package com.nalexand.swingy.ui.console.output;

import com.nalexand.swingy.controller.BattleController;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.models.Battle;
import com.nalexand.swingy.model.models.Hero;
import com.nalexand.swingy.ui.console.BaseConsoleOutput;
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
        printFormat("%s  vs  %s\n", heroTitle, mobTitle);
        battle.getSteps().forEach(step -> println(step.getMessage()));
        println("1: Ok!");
        listenCommand(Command.KEY_1, controller::accept);
    }
}
