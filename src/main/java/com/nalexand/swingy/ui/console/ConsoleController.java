package com.nalexand.swingy.ui.console;

import com.nalexand.swingy.model.ModelFacade;
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
                result = Command.KEY_1;
                break;
            case "2":
                result = Command.KEY_2;
                break;
            case "3":
                result = Command.KEY_3;
                break;
            case "4":
                result = Command.KEY_4;
                break;
            case "w":
                result = Command.KEY_W;
                break;
            case "a":
                result = Command.KEY_A;
                break;
            case "s":
                result = Command.KEY_S;
                break;
            case "d":
                result = Command.KEY_D;
                break;
        }
        return result;
    }
}
