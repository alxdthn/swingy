package com.nalexand.swingy.ui.gui;

import javax.swing.*;
import java.awt.*;

public class Theme {

    public static void apply(Component component) {
        if (component instanceof JPanel) {
            for (Component child : ((JPanel) component).getComponents()) {
                apply(child);
            }
        } else if (component instanceof JButton) {
            applyButton((JButton) component);
        }
        else if (component instanceof JLabel) {
            applyLabel((JLabel) component);
        }
    }

    private static void applyButton(JButton button) {
        button.setFont(getFont("Phosphate", -1, 20, button.getFont()));
        button.setMargin(new Insets(8, 8, 8, 8));
    }

    @SuppressWarnings("DuplicatedCode")
    private static Font getFont(String fontName, int style, int size, Font currentFont) {
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

    private static void applyLabel(JLabel label) {
        JLabel temp = new JLabel();
        if (label.getFont().equals(temp.getFont()))
            label.setFont(getFont("Phosphate", -1, 16, label.getFont()));
    }
}
