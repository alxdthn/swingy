package com.nalexand.swingy.ui.console.output;

import com.nalexand.swingy.controller.WelcomeController;
import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.ui.console.BaseConsoleOutput;
import com.nalexand.swingy.ui.console.Command;

import static com.nalexand.swingy.ui.console.output.OutputUtils.printDash;
import static com.nalexand.swingy.utils.Utils.printFormat;
import static com.nalexand.swingy.utils.Utils.println;

public class WelcomeOutput extends BaseConsoleOutput {

    public WelcomeOutput(ModelFacade model, WelcomeController controller) {
        printDash();

        println("Select hero");
        println("1: Create a hero");
        listenCommand(Command.KEY_1, controller::showCreateHero);

        int option = 2;
        Command[] commands = {Command.KEY_2, Command.KEY_3, Command.KEY_4, Command.KEY_5};
        for (Hero hero : model.getCreatedHeroes()) {
            printFormat("%d: %s\n", option, hero.name);
            listenCommand(commands[option - 2], () -> controller.selectHeroAndShowGameProcess(hero));
            option++;
        }
    }
}
