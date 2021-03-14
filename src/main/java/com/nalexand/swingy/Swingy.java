package com.nalexand.swingy;

import com.nalexand.swingy.model.scenario.ModelFacade;
import com.nalexand.swingy.ui.console.ConsoleUserInterface;
import com.nalexand.swingy.ui.gui.GuiUserInterface;
import com.nalexand.swingy.ui.gui.GuiView;

public class Swingy {

    public static final String GAME_DATA_FILE = "./game_data.json";

    public static final boolean IGNORE_SAVED = true;

    static final ModelFacade model = new ModelFacade();

    static final ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface(model);

    static final GuiUserInterface guiUserInterface = new GuiUserInterface(model, new GuiView());

    public static void main(String[] args) {
        boolean isValid = args.length == 1 && args[0].matches("(console|gui)");

        if (!isValid) {
            System.out.println("Expected args:\n  console: console mode\n  gui: window mode");
            System.exit(1);
        }

        System.out.println("Swingy!");

        switch (args[0]) {
            case "console":
                consoleUserInterface.start(model);
                break;
            case "gui":
                guiUserInterface.start(model);
                break;
        }
    }
}
