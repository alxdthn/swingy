package com.nalexand.swingy.ui.gui.forms;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.nalexand.swingy.controller.GameProcessController;
import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.ui.base.KeyListenerForm;
import com.nalexand.swingy.ui.gui.custom.WorldMapPanel;
import com.nalexand.swingy.ui.gui.utils.IconResolver;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameProcessForm extends KeyListenerForm {
    private JPanel panel;
    private JPanel menu;
    private JLabel heroIcon;
    private JLabel levelLabel;
    private JLabel xpLabel;
    private JLabel hpLabel;
    private JLabel attackLabel;
    private JLabel defenceLabel;
    private JPanel map;

    public GameProcessForm(ModelFacade model, GameProcessController controller) {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();

        listenKey(KeyEvent.VK_W, () -> controller.moveHero(0, -1));
        listenKey(KeyEvent.VK_A, () -> controller.moveHero(-1, 0));
        listenKey(KeyEvent.VK_S, () -> controller.moveHero(0, 1));
        listenKey(KeyEvent.VK_D, () -> controller.moveHero(1, 0));

        Hero hero = model.getSelectedHero();

        //region Draw menu
        ((TitledBorder) menu.getBorder()).setTitle(hero.name);
        IconResolver.setIcon(heroIcon, hero.iconSource);
        levelLabel.setText(levelLabel.getText() + " " + hero.getLevel());
        xpLabel.setText(xpLabel.getText() + hero.getXp());
        hpLabel.setText(hpLabel.getText() + String.format("%d/%d", hero.currentHitPoints, hero.getMaxHitPoints()));
        attackLabel.setText(attackLabel.getText() + hero.getAttack());
        defenceLabel.setText(defenceLabel.getText() + hero.getDefence());
        //endregion

        //region Draw map
        WorldMapPanel worldMapPanel = new WorldMapPanel(model);
        worldMapPanel.setLayout(new GridBagLayout());
        map.add(worldMapPanel, BorderLayout.CENTER);
        //endregion
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 0));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16), null));
        menu = new JPanel();
        menu.setLayout(new GridLayoutManager(7, 1, new Insets(16, 16, 16, 16), -1, -1));
        panel.add(menu, BorderLayout.WEST);
        menu.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "<Hero name>", TitledBorder.CENTER, TitledBorder.BELOW_TOP, this.$$$getFont$$$(null, -1, -1, menu.getFont())));
        heroIcon = new JLabel();
        heroIcon.setHorizontalAlignment(2);
        heroIcon.setIcon(new ImageIcon(getClass().getResource("/traxex.png")));
        heroIcon.setInheritsPopupMenu(false);
        heroIcon.setText("");
        heroIcon.setVerticalAlignment(1);
        heroIcon.setVerticalTextPosition(3);
        menu.add(heroIcon, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        levelLabel = new JLabel();
        levelLabel.setText("Level: ");
        menu.add(levelLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        menu.add(panel1, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        xpLabel = new JLabel();
        xpLabel.setText("XP: ");
        menu.add(xpLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        hpLabel = new JLabel();
        hpLabel.setText("HP: ");
        menu.add(hpLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        attackLabel = new JLabel();
        attackLabel.setText("Attack: ");
        menu.add(attackLabel, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        defenceLabel = new JLabel();
        defenceLabel.setText("Defence: ");
        menu.add(defenceLabel, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        map = new JPanel();
        map.setLayout(new BorderLayout(0, 0));
        panel.add(map, BorderLayout.CENTER);
        map.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "World map", TitledBorder.CENTER, TitledBorder.BELOW_TOP));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

    @Override
    public JComponent getRootComponent() {
        return $$$getRootComponent$$$();
    }
}
