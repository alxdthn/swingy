package com.nalexand.swingy.ui.gui;

import com.nalexand.swingy.model.scenario.CreateHero;
import com.nalexand.swingy.model.scenario.GameProcess;
import com.nalexand.swingy.model.scenario.Welcome;
import com.nalexand.swingy.ui.base.BaseView;

import javax.swing.*;
import java.awt.*;

public final class GuiView extends BaseView {

    private final JFrame frame = new JFrame("com.nalexand.swingy.Swingy");

    private JPanel currentPane = null;

    private GuiController guiController;

    public void initController(GuiController guiController) {
        this.guiController = guiController;
    }

    public void initGui() {
        SwingUtilities.invokeLater(this::initGuiInternal);
    }

    @Override
    protected void showWelcome(Welcome welcome) {
        SwingUtilities.invokeLater(() -> renderWelcome(welcome));
    }

    @Override
    protected void showCreateHero(CreateHero createHero) {
        SwingUtilities.invokeLater(() -> renderCreateNewHero(createHero));
    }

    @Override
    protected void showGameProcess(GameProcess gameProcess) {
        throw new IllegalStateException();
    }

    private void renderWelcome(Welcome welcome) {
        JPanel contents = new JPanel();
        contents.setLayout(new BoxLayout(contents, BoxLayout.Y_AXIS));

        addTitle(contents,"Select hero");

        JButton createNewHeroButton = new JButton("Create new hero");
        createNewHeroButton.addActionListener(e -> guiController.createNewHeroButton());
        contents.add(createNewHeroButton);

        if (welcome.createdHeroes != null) {
            JButton selectPrevHeroButton = new JButton("Select a previously created hero");
            contents.add(selectPrevHeroButton);
        }
        render(contents);
    }

    private void renderCreateNewHero(CreateHero createHero) {
        JPanel contents = new JPanel();
        contents.setLayout(new BoxLayout(contents, BoxLayout.PAGE_AXIS));

        addTitle(contents,"Create new hero!!");

        render(contents);
    }

    private void initGuiInternal() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = new Dimension(screenSize);

        windowSize.width *= 0.8;
        windowSize.height *= 0.8;

        Point location = new Point(
                (screenSize.width - windowSize.width) / 2,
                (screenSize.height - windowSize.height) / 2
        );

        frame.setLayout(new BorderLayout());
        frame.setLocation(location);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(windowSize);
        frame.setVisible(true);
    }

    private void render(JPanel newContent) {
        if (currentPane != null) {
            frame.getContentPane().remove(currentPane);
        }
        currentPane = newContent;
        frame.getContentPane().add(newContent, BorderLayout.CENTER);
        frame.pack();
    }

    private void addTitle(JPanel contents, String title) {
        JLabel result = new JLabel(title);
        result.getFont().deriveFont(164f);
        result.getFont().deriveFont(Font.BOLD);
        contents.add(result);
    }
}
