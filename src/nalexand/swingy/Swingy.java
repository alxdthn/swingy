package nalexand.swingy;

import nalexand.swingy.model.Model;
import nalexand.swingy.ui.console.ConsoleUserInterface;
import nalexand.swingy.ui.gui.GuiUserInterface;

public class Swingy {

    static final Model model = new Model();

    static final ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface(model);

    static final GuiUserInterface guiUserInterface = new GuiUserInterface(model);

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
