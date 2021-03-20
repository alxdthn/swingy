package com.nalexand.swingy.ui.gui;

import com.nalexand.swingy.controller.*;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.scenario.BaseScenarioStep;
import com.nalexand.swingy.ui.base.BaseView;
import com.nalexand.swingy.ui.gui.forms.CreateHeroForm;
import com.nalexand.swingy.ui.gui.forms.WelcomeForm;

import javax.swing.*;
import java.awt.*;

public final class GuiView extends BaseView {

    private JFrame frame = null;

    private JComponent currentPane = null;

    @Override
    public void renderScenarioData(BaseScenarioStep scenarioStep) {
        SwingUtilities.invokeLater(() -> super.renderScenarioData(scenarioStep));
    }

    @Override
    protected void showWelcome(ModelFacade model, WelcomeController controller) {
        render(new WelcomeForm(model, controller).$$$getRootComponent$$$());
    }

    @Override
    protected void showCreateHero(ModelFacade model, CreateHeroController controller) {
        render(new CreateHeroForm().$$$getRootComponent$$$());
    }

    @Override
    protected void showGameProcess(ModelFacade model, GameProcessController controller) {
        throw new IllegalStateException();
    }

    @Override
    protected void showBattleConfirmation(ModelFacade model, DialogController controller) {

    }

    @Override
    protected void showBattle(ModelFacade model) {

    }

    @Override
    protected void showBattleWin(ModelFacade model, BattleWinController controller) {

    }

    @Override
    protected void showBattleLose(ModelFacade model, DialogController controller) {

    }

    private void render(JComponent newContent) {
        if (currentPane != null) {
            frame.getContentPane().remove(currentPane);
        }
        currentPane = newContent;
        frame.getContentPane().add(newContent, BorderLayout.CENTER);
        frame.pack();
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
