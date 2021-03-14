package nalexand.swingy.ui.console;

import nalexand.swingy.model.Model;
import nalexand.swingy.ui.Command;
import nalexand.swingy.ui.Controller;

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
        String line;

        model.start();

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
