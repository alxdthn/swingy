package nalexand.swingy.ui.console;

import nalexand.swingy.model.Model;
import nalexand.swingy.ui.UserInterface;

public class ConsoleUserInterface extends UserInterface<ConsoleController, ConsoleView> {

    public ConsoleUserInterface(Model model) {
        super(new ConsoleController(model), new ConsoleView());
    }
}
