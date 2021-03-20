package com.nalexand.swingy;

import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.ui.console.ConsoleView;
import com.nalexand.swingy.ui.gui.GuiView;

public class Swingy {

    public static final String GAME_DATA_FILE = "./game_data.json";

    public static final boolean IGNORE_SAVED = false;

    public static final double OBSTACLES_PERCENTAGE = 0.25;

    public static final int ITEM_GENERATION_PERCENTAGE = 21;

    public static final String[] MOB_NAMES = {"Ork", "Necromancer", "Bandit", "Wood Elf"};

    static final ModelFacade model = new ModelFacade();

    static final ConsoleView consoleView = new ConsoleView();

    static final GuiView guiView = new GuiView();

    public static void main(String[] args) {
        boolean isValid = args.length == 1 && args[0].matches("(console|gui)");

        if (!isValid) {
            System.out.println("Expected args:\n  console: console mode\n  gui: window mode");
            System.exit(1);
        }

        System.out.println("Swingy!");

        switch (args[0]) {
            case "console":
                model.setView(consoleView);
                model.render();
                consoleView.start();
                break;
            case "gui":
                model.setView(guiView);
                guiView.initGui();
                model.render();
                break;
        }
    }
}
