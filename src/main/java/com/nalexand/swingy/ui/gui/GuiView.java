package com.nalexand.swingy.ui.gui;

import com.nalexand.swingy.Swingy;
import com.nalexand.swingy.controller.*;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.scenario.BaseScenarioStep;
import com.nalexand.swingy.ui.base.BaseView;
import com.nalexand.swingy.ui.base.Form;
import com.nalexand.swingy.ui.gui.forms.*;
import com.nalexand.swingy.utils.Utils;

import javax.swing.*;
import java.awt.*;

import static java.awt.event.KeyEvent.VK_Q;

public final class GuiView extends BaseView {

    public final static String NAME = "gui";

    private JFrame frame = null;

    private JComponent currentPane = null;

    @Override
    public void renderScenarioData(BaseScenarioStep scenarioStep) {
        SwingUtilities.invokeLater(() -> super.renderScenarioData(scenarioStep));
    }

    @Override
    public void start(ModelFacade model) {
        model.setView(this);
        initGui();
        model.render();
    }

    @Override
    public void stop() {
        frame.setVisible(false);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    protected void showWelcome(ModelFacade model, WelcomeController controller) {
        render(new WelcomeForm(model, controller));
    }

    @Override
    protected void showCreateHero(ModelFacade model, CreateHeroController controller) {
        render(new CreateHeroForm(model, controller));
    }

    @Override
    protected void showGameProcess(ModelFacade model, GameProcessController controller) {
        render(new GameProcessForm(model, controller));
    }

    @Override
    protected void showBattleConfirmation(ModelFacade model, DialogController controller) {
        render(new BattleConfirmationForm(model, controller));
    }

    @Override
    protected void showBattle(ModelFacade model, BattleController controller) {
        render(new ShowBattleForm(model, controller));
    }

    @Override
    protected void showBattleWin(ModelFacade model, BattleWinController controller) {
        render(new BattleWinForm(model, controller));
    }

    @Override
    protected void showBattleLose(ModelFacade model, DialogController controller) {
        render(new BattleLoseForm(model, controller));
    }

    private void render(Form form) {
        if (currentPane != null) {
            frame.getContentPane().remove(currentPane);
        }
        currentPane = form.getRootComponent();
        Theme.apply(currentPane);
        frame.getContentPane().add(currentPane, BorderLayout.CENTER);
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

        Utils.listenKey(frame.getRootPane(), VK_Q, Swingy::switchView);
    }
}
