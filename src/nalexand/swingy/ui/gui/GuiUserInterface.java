package nalexand.swingy.ui.gui;

import nalexand.swingy.model.Model;
import nalexand.swingy.ui.UserInterface;

public class GuiUserInterface extends UserInterface<GuiController, GuiView> {

    public GuiUserInterface(Model model) {
        super(new GuiController(model), new GuiView());
    }
}