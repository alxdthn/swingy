package com.nalexand.swingy.ui.gui;

import com.nalexand.swingy.controller.*;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.scenario.BaseScenarioStep;
import com.nalexand.swingy.ui.base.BaseView;
import com.nalexand.swingy.ui.base.Form;
import com.nalexand.swingy.ui.base.KeyListenerForm;
import com.nalexand.swingy.ui.gui.forms.BattleConfirmationForm;
import com.nalexand.swingy.ui.gui.forms.CreateHeroForm;
import com.nalexand.swingy.ui.gui.forms.GameProcessForm;
import com.nalexand.swingy.ui.gui.forms.WelcomeForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class GuiView extends BaseView implements KeyListener {

    private JFrame frame = null;

    private JComponent currentPane = null;

    private Form currentForm = null;

    @Override
    public void renderScenarioData(BaseScenarioStep scenarioStep) {
        SwingUtilities.invokeLater(() -> super.renderScenarioData(scenarioStep));
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
//        showDialog(new BattleConf(model, controller));
        render(new BattleConfirmationForm(model, controller));
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

    private void render(Form form) {
        currentForm = form;
        if (currentPane != null) {
            frame.getContentPane().remove(currentPane);
        }
        currentPane = form.getRootComponent();
        frame.getContentPane().add(currentPane, BorderLayout.CENTER);
        frame.pack();
    }

    private void showDialog(Form form) {
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

        frame.addKeyListener(this);
        frame.setLayout(new BorderLayout());
        frame.setLocation(location);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(windowSize);
        frame.setVisible(true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (currentForm instanceof KeyListenerForm) {
            ((KeyListenerForm) currentForm).notifyCommandListeners(e.getKeyCode());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
}
