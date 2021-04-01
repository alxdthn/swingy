package com.nalexand.swingy.ui.gui.forms;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.nalexand.swingy.controller.BattleController;
import com.nalexand.swingy.model.Battle;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.ui.base.Form;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

;

public class ShowBattleForm implements Form {

    private JLabel hero;
    private JLabel mob;
    private JLabel title;
    private JPanel panel;
    private JButton okButton;
    private JPanel battleResult;
    private JPanel content;
    private JPanel heroPanel;
    private JPanel mobPanel;
    private JLabel heroLabel;
    private JLabel mobLabel;
    private JScrollPane contentScroll;

    public ShowBattleForm(ModelFacade model, BattleController controller) {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();

        Battle battle = model.getBattle();
        heroLabel.setText(model.getSelectedHero().name);
        mobLabel.setText(battle.mob.name);
        HeroForm heroForm = new HeroForm(model.getSelectedHero(), battle.heroStartHp);
        heroPanel.add(heroForm.getPanel());
        HeroForm mobHeroForm = new HeroForm(battle.mob, battle.mobStartHp);
        mobPanel.add(mobHeroForm.getPanel());

        List<Battle.Step> steps = battle.getSteps();


        battleResult.setLayout(new GridLayoutManager(steps.size() + 1, 1, new Insets(0, 0, 0, 0), -1, -1));

        int row = 1;
        for (Battle.Step step : steps) {
            JLabel cell = new JLabel("    " + step.getMessage() + "      ");
            cell.setHorizontalAlignment(SwingConstants.LEFT);
            cell.setHorizontalTextPosition(SwingConstants.LEFT);
            cell.setFont(new Font("Bughad", -1, 20));
            if (row % 2 == 1) {
                cell.setForeground(new Color(0x009900));
            } else {
                cell.setForeground(new Color(0x990000));
            }

            battleResult.add(
                    cell,
                    new GridConstraints(row, 0, 1, 1,
                            GridConstraints.ANCHOR_CENTER,
                            GridConstraints.FILL_BOTH,
                            GridConstraints.SIZEPOLICY_FIXED,
                            GridConstraints.SIZEPOLICY_FIXED,
                            null,
                            new Dimension(100, 32),
                            null,
                            0,
                            false)
            );
            row++;
        }
        okButton.addActionListener(e -> controller.accept());
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
        panel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        content = new JPanel();
        content.setLayout(new GridLayoutManager(5, 2, new Insets(32, 32, 32, 32), -1, -1));
        panel.add(content, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        content.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), null, TitledBorder.CENTER, TitledBorder.BELOW_TOP, this.$$$getFont$$$("Phosphate", -1, 36, content.getFont()), new Color(-4473925)));
        okButton = new JButton();
        Font okButtonFont = this.$$$getFont$$$("Phosphate", -1, 20, okButton.getFont());
        if (okButtonFont != null) okButton.setFont(okButtonFont);
        okButton.setHideActionText(false);
        okButton.setMargin(new Insets(8, 8, 8, 8));
        okButton.setText("ok");
        content.add(okButton, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        contentScroll = new JScrollPane();
        contentScroll.setHorizontalScrollBarPolicy(31);
        content.add(contentScroll, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        battleResult = new JPanel();
        battleResult.setLayout(new GridBagLayout());
        battleResult.setAutoscrolls(true);
        Font battleResultFont = this.$$$getFont$$$("Phosphate", -1, 8, battleResult.getFont());
        if (battleResultFont != null) battleResult.setFont(battleResultFont);
        contentScroll.setViewportView(battleResult);
        mobLabel = new JLabel();
        Font mobLabelFont = this.$$$getFont$$$("Phosphate", -1, 28, mobLabel.getFont());
        if (mobLabelFont != null) mobLabel.setFont(mobLabelFont);
        mobLabel.setHorizontalAlignment(10);
        mobLabel.setHorizontalTextPosition(11);
        mobLabel.setText("MOB");
        content.add(mobLabel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        heroLabel = new JLabel();
        Font heroLabelFont = this.$$$getFont$$$("Phosphate", -1, 28, heroLabel.getFont());
        if (heroLabelFont != null) heroLabel.setFont(heroLabelFont);
        heroLabel.setHorizontalTextPosition(11);
        heroLabel.setText("HERO");
        content.add(heroLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mobPanel = new JPanel();
        mobPanel.setLayout(new GridBagLayout());
        content.add(mobPanel, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        title = new JLabel();
        Font titleFont = this.$$$getFont$$$("Phosphate", -1, 36, title.getFont());
        if (titleFont != null) title.setFont(titleFont);
        title.setForeground(new Color(-16777216));
        title.setHorizontalAlignment(0);
        title.setHorizontalTextPosition(0);
        title.setText("Battle");
        content.add(title, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        heroPanel = new JPanel();
        heroPanel.setLayout(new GridBagLayout());
        content.add(heroPanel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
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
        return panel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
