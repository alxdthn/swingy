package com.nalexand.swingy.ui.gui.forms;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.nalexand.swingy.controller.BattleWinController;
import com.nalexand.swingy.model.Battle;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.items.Item;
import com.nalexand.swingy.ui.gui.Form;
import com.nalexand.swingy.utils.IconResolver;

import javax.swing.*;
import java.awt.*;

public class BattleWinForm implements Form {
    private JPanel root;
    private JPanel content;
    private JLabel title;
    private JLabel xp;
    private JButton accept;
    private JButton loot;
    private JLabel lootTitle;
    private JLabel winAnim;

    public BattleWinForm(ModelFacade model, BattleWinController controller) {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
        Battle battle = model.getBattle();

        xp.setText("+" + battle.xp + "xp");

        Item mobItem = battle.mob.getItem();
        if (mobItem != null) {
            IconResolver.setIcon(loot, mobItem.iconSource);
            loot.setText(
                    String.format("<html>%s<br/>%s<html>", mobItem.name, mobItem.getDisplayedString())
            );
        } else {
            lootTitle.setVisible(false);
            loot.setVisible(false);
        }

        loot.addActionListener(event -> controller.takeLootItem(mobItem));
        accept.addActionListener(event -> controller.accept());
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        root = new JPanel();
        root.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        content = new JPanel();
        content.setLayout(new GridLayoutManager(7, 1, new Insets(32, 32, 32, 32), -1, -1));
        root.add(content, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        content.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), null));
        title = new JLabel();
        Font titleFont = this.$$$getFont$$$("Phosphate", -1, 36, title.getFont());
        if (titleFont != null) title.setFont(titleFont);
        title.setText("Win!");
        content.add(title, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        xp = new JLabel();
        Font xpFont = this.$$$getFont$$$("Phosphate", -1, 24, xp.getFont());
        if (xpFont != null) xp.setFont(xpFont);
        xp.setText("XP");
        content.add(xp, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        accept = new JButton();
        Font acceptFont = this.$$$getFont$$$("Phosphate", -1, 20, accept.getFont());
        if (acceptFont != null) accept.setFont(acceptFont);
        accept.setText("OK");
        content.add(accept, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        content.add(spacer1, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 32), null, 0, false));
        loot = new JButton();
        Font lootFont = this.$$$getFont$$$("Phosphate", -1, 20, loot.getFont());
        if (lootFont != null) loot.setFont(lootFont);
        loot.setHorizontalAlignment(2);
        loot.setHorizontalTextPosition(4);
        loot.setMargin(new Insets(8, 8, 8, 8));
        loot.setText("<item>");
        content.add(loot, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lootTitle = new JLabel();
        Font lootTitleFont = this.$$$getFont$$$("Phosphate", -1, 18, lootTitle.getFont());
        if (lootTitleFont != null) lootTitle.setFont(lootTitleFont);
        lootTitle.setText("Your loot:");
        content.add(lootTitle, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        winAnim = new JLabel();
        winAnim.setEnabled(true);
        winAnim.setIcon(new ImageIcon(getClass().getResource("/feels_good_pepe.gif")));
        winAnim.setText("");
        content.add(winAnim, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(128, 128), null, 0, false));
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
        return root;
    }

    @Override
    public JComponent getRootComponent() {
        return $$$getRootComponent$$$();
    }
}
