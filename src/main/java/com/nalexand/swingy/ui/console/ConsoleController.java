package com.nalexand.swingy.ui.console;

import com.nalexand.swingy.model.scenario.ModelFacade;
import com.nalexand.swingy.ui.Command;
import com.nalexand.swingy.ui.base.Controller;

import java.util.NoSuchElementException;
import java.util.Scanner;

public final class ConsoleController implements Controller {

    private final Scanner scanner = new Scanner(System.in);

    private final ModelFacade model;

    public ConsoleController(ModelFacade model) {
        this.model = model;
    }

    @Override
    public void start() {
        String line;

        model.render();

        while ((line = getLine()) != null) {
            Command command = getCommand(line);
            model.resolveCommand(command);
        }
        close();
    }

    @Override
    public void close() {
        System.exit(0);
    }

    private String getLine() {
        String result = null;

        try {
            result = scanner.nextLine();
        } catch (NoSuchElementException | IllegalStateException e) {
            close();
        }
        return result;
    }

    private Command getCommand(String line) {
        Command result = Command.UNKNOWN;

        switch (line) {
            case "1":
                result = Command.OPTION_1;
                break;
            case "2":
                result = Command.OPTION_2;
                break;
            case "3":
                result = Command.OPTION_3;
                break;
            case "4":
                result = Command.OPTION_4;
                break;
        }
        return result;
    }
}
