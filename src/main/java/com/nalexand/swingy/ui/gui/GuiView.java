package com.nalexand.swingy.ui.gui;

import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.ui.base.BaseView;
import com.nalexand.swingy.ui.base.Controller;
import com.nalexand.swingy.ui.gui.forms.CreateHeroForm;

import javax.swing.*;
import java.awt.*;

public final class GuiView extends BaseView implements Controller {

    private JFrame frame = null;

    private JPanel currentPane = null;

    @Override
    protected void showWelcome(ModelFacade model) {
        SwingUtilities.invokeLater(() -> renderWelcome(model));
    }

    @Override
    protected void showCreateHero(ModelFacade model) {
        SwingUtilities.invokeLater(() -> renderCreateNewHero(model));
    }

    @Override
    protected void showGameProcess(ModelFacade model) {
        throw new IllegalStateException();
    }

    @Override
    protected void showBattleConfirmation(ModelFacade model) {

    }

    @Override
    protected void showBattle(ModelFacade model) {

    }

    @Override
    protected void showBattleWin(ModelFacade model) {

    }

    @Override
    protected void showBattleLose(ModelFacade model) {

    }

    private void renderWelcome(ModelFacade model) {
        JPanel createHero = new CreateHeroForm(model).getPanel1();
        render(createHero);
    }

    private void renderCreateNewHero(ModelFacade model) {

    }

    private void render(JPanel newContent) {
        if (currentPane != null) {
            frame.getContentPane().remove(currentPane);
        }
        currentPane = newContent;
        frame.getContentPane().add(newContent, BorderLayout.CENTER);
        frame.pack();
    }

    @Override
    public void start() {
        initGui();
    }

    @Override
    public void close() {

    }

    public void initGui() {
        frame = new JFrame("Swingy");
        SwingUtilities.invokeLater(this::initGuiInternal);
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
}
