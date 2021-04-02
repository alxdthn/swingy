package com.nalexand.swingy;

import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.SaveMethod;
import com.nalexand.swingy.ui.View;
import com.nalexand.swingy.ui.console.ConsoleView;
import com.nalexand.swingy.ui.gui.GuiView;

public class Swingy {

    public static final String GAME_DATA_FILE = "./game_data.json";

    public static final boolean IGNORE_SAVED = false;

    public static final boolean DEBUG = true;

    public static final SaveMethod SAVE_METHOD = SaveMethod.DATA_BASE;

    public static final String[] MOB_NAMES = {"Ork", "Necromancer", "Bandit", "Wood Elf"};

    private static final ModelFacade model = new ModelFacade();

    private static View currentView = null;

    private static GuiView guiView = new GuiView();

    public static void main(String[] args) {
        boolean isValid = args.length == 1 && args[0].matches("(console|gui)");

        if (!isValid) {
            System.out.println("Expected args:\n  console: console mode\n  gui: window mode");
            System.exit(1);
        }

        System.out.println("Swingy!");

        switch (args[0]) {
            case "console":
                currentView = new ConsoleView();
                break;
            case "gui":
                currentView = guiView;
                break;
        }
        currentView.start(model);
    }

    public static void switchView() {
        currentView.stop();
        if (currentView.getName().equals(GuiView.NAME)) {
            currentView = new ConsoleView();
        } else {
            currentView = guiView;
        }
        currentView.start(model);
    }
}
