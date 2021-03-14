package nalexand.swingy.ui.gui;

import nalexand.swingy.model.Model;
import nalexand.swingy.ui.Controller;

import javax.swing.*;
import java.awt.*;

public class GuiController implements Controller {

    private final Model model;

    public GuiController(Model model) {
        this.model = model;
    }

    @Override
    public void start() {
        javax.swing.SwingUtilities.invokeLater(this::initGui);
    }


    @Override
    public void close() {

    }

    private void initGui() {
        JFrame frame = new JFrame("Swingy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Test label");
        frame.getContentPane().add(label);

        frame.setPreferredSize(new Dimension(200, 100));

        frame.pack();
        frame.setVisible(true);
    }
}
