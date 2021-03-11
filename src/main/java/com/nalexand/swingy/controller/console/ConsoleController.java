package com.nalexand.swingy.controller.console;

import com.nalexand.swingy.controller.Command;
import com.nalexand.swingy.controller.Controller;
import com.nalexand.swingy.model.Model;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleController implements Controller {

    private final Scanner scanner = new Scanner(System.in);

    private final Model model;

    public ConsoleController(Model model) {
        this.model = model;
    }

    @Override
    public void start() {
        waitCommand();
    }

    @Override
    public void waitCommand() {
        String line;

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
        }
        return result;
    }
}
