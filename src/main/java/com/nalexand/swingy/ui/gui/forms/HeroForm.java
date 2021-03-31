package com.nalexand.swingy.ui.gui.forms;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.nalexand.swingy.controller.DialogController;
import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.items.Item;
import com.nalexand.swingy.ui.base.Form;
import com.nalexand.swingy.ui.gui.utils.IconResolver;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HeroForm implements Form {
    private JPanel panel;
    private JLabel modIcon;
    private JLabel hp;
    private JLabel level;
    private JLabel item;
    private JLabel attack;
    private JLabel defence;
    private JPanel content;

    public HeroForm(Hero hero) {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
        IconResolver.setIcon(modIcon, hero.iconSource);
        hp.setText("HP: " + hero.currentHitPoints);
        level.setText("Level: " + hero.level);
        Item heroItem = hero.getItem();
        if (heroItem != null) {
            IconResolver.setIcon(item, heroItem.iconSource);
            item.setText(String.format("<html>%s<br/>%s</html>", heroItem.name, heroItem.getDisplayedString()));
        }
        attack.setText("Attack: " + hero.getAttack());
        defence.setText("Defence: " + hero.getDefence());

    }

    public JPanel getPanel() {
        return panel;
    }

    @Override
    public JComponent getRootComponent() {
        return panel;
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
        panel.setLayout(new GridLayoutManager(1, 1, new Insets(16, 16, 16, 16), -1, -1));
        content = new JPanel();
        content.setLayout(new GridLayoutManager(6, 1, new Insets(32, 32, 32, 32), -1, -1));
        panel.add(content, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        modIcon = new JLabel();
        modIcon.setHorizontalAlignment(0);
        modIcon.setHorizontalTextPosition(0);
        modIcon.setIcon(new ImageIcon(getClass().getResource("/bandit.png")));
        modIcon.setText("");
        modIcon.setVerticalAlignment(0);
        content.add(modIcon, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        level = new JLabel();
        level.setText("Label");
        content.add(level, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        hp = new JLabel();
        hp.setText("Label");
        content.add(hp, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        attack = new JLabel();
        attack.setText("Label");
        content.add(attack, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        defence = new JLabel();
        defence.setText("Label");
        content.add(defence, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        item = new JLabel();
        item.setText("");
        content.add(item, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}