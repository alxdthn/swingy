package com.nalexand.swingy.ui.base;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;

public abstract class KeyListenerForm implements Form {

    protected final void listenKey(int key, KeyListener listener) {
        String actionMapKey = Integer.toString(key);
        getRootComponent().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(key, 0),
                actionMapKey
        );
        getRootComponent().getActionMap().put(
                actionMapKey,
                new AbstractAction(actionMapKey) {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        listener.invoke();
                    }
                }
        );
    }

    protected interface KeyListener {

        void invoke();
    }
}
