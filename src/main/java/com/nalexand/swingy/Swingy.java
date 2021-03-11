package com.nalexand.swingy;

import com.nalexand.swingy.controller.Controller;
import com.nalexand.swingy.controller.ControllerType;
import com.nalexand.swingy.controller.console.ConsoleController;
import com.nalexand.swingy.model.Model;
import com.nalexand.swingy.view.ViewType;

import java.util.HashMap;
import java.util.Map;

public class Swingy {

    static Map<ControllerType, Controller> controller = new HashMap<>();

    static ControllerType currentController;

    static Model model;

    public static void main(String[] args) {

        System.out.println("Swingy!");

        validateArgs(args);

        controller.put(ControllerType.CONSOLE, new ConsoleController(model));

        controller.get(currentController).start();
    }

    static void validateArgs(String[] args) {
        boolean isValid = args.length == 1 && (args[0].equals("gui") || args[0].equals("console"));

        if (!isValid) {
            System.out.println("Expected args:\n  gui: window mode\n  console: console mode");
            System.exit(1);
        }
        switch (args[0]) {
            case "gui":
                currentController = ControllerType.GUI;
                model = new Model(ViewType.GUI);
                break;
            case "console":
                currentController = ControllerType.CONSOLE;
                model = new Model(ViewType.CONSOLE);
                break;
        }
    }
}
