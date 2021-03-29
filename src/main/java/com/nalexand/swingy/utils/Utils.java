package com.nalexand.swingy.utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;

public class Utils {

    private final static Random random = new Random();

    private Utils() {}

    public static void println(String line) {
        System.out.println(line);
    }

    public static int printFormat(String format, Object... args) {
        String formatted = String.format(format, args);
        System.out.print(formatted);
        return formatted.length();
    }

    public static boolean randomByPercent(int percentage) {
        return random.nextInt(100) < percentage;
    }

    public static int randomBetween(int low, int high) {
        return random.nextInt(high-low) + low;
    }

    public static <T> List<T> listOfNotNull(T... items) {
        List<T> result = new ArrayList<>();
        for (T item : items) {
            if (item != null) {
                result.add(item);
            }
        }
        return result;
    }

    public static void listenKey(JComponent component, int key, Utils.KeyListener listener) {
        String actionMapKey = Integer.toString(key);
        component.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(key, 0),
                actionMapKey
        );
        component.getActionMap().put(
                actionMapKey,
                new AbstractAction(actionMapKey) {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        listener.invoke();
                    }
                }
        );
    }

    public interface KeyListener {

        void invoke();
    }
}
