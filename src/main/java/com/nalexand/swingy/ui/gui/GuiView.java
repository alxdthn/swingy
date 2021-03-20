package com.nalexand.swingy.ui.gui;

import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.ui.base.BaseView;

import javax.swing.*;
import java.awt.*;

public final class GuiView extends BaseView {

    private JFrame frame = null;

    private JPanel currentPane = null;

    private GuiController guiController;

    public void initController(GuiController guiController) {
        this.guiController = guiController;
    }

    public void initGui() {
        frame = new JFrame("Swingy");
        SwingUtilities.invokeLater(this::initGuiInternal);
    }

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
