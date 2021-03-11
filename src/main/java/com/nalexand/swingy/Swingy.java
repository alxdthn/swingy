package com.nalexand.swingy;

import com.nalexand.swingy.view.TerminalView;
import com.nalexand.swingy.view.View;
import com.nalexand.swingy.view.ViewType;

import java.util.HashMap;
import java.util.Map;

public class Swingy {

    static Map<ViewType, View> view = new HashMap<>();

    static ViewType currentView;

    public static void main(String[] args) {

        System.out.println("Swingy!");

        view.put(ViewType.CONSOLE, new TerminalView());

        validateArgs(args);

        view.get(currentView).process();
    }

    static void validateArgs(String[] args) {
        boolean isValid = args.length == 1 && (args[0].equals("gui") || args[0].equals("console"));

        if (!isValid) {
            System.out.println("Expected args:\n  gui: window mode\n  console: console mode");
            System.exit(1);
        }
        switch (args[0]) {
            case "gui":
                currentView = ViewType.GUI;
                break;
            case "console":
                currentView = ViewType.CONSOLE;
                break;
        }
    }
}
