package com.nalexand.swingy.ui.console.output;

import com.nalexand.swingy.controller.CreateHeroController;
import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.ui.base.BaseConsoleOutput;
import com.nalexand.swingy.ui.console.Command;

import static com.nalexand.swingy.ui.console.output.OutputUtils.printDash;
import static com.nalexand.swingy.utils.Utils.printFormat;
import static com.nalexand.swingy.utils.Utils.println;

public class CreateHeroOutput extends BaseConsoleOutput {

    public CreateHeroOutput(ModelFacade model, CreateHeroController controller) {
        printDash();

        println("Create hero");

        int option = 1;
        Command[] options = {Command.KEY_1, Command.KEY_2, Command.KEY_3, Command.KEY_4};
        for (Hero hero : model.getAvailableForCreateHeroes()) {
            printFormat("%d: %s\n", option, hero.name);
            listenCommand(options[option - 1], () -> controller.createHero(hero));
            option++;
        }
    }
}
