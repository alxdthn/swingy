package com.nalexand.swingy.ui.base;

import java.util.HashMap;
import java.util.Map;

public abstract class KeyListenerForm implements Form {

    private Map<Integer, KeyListener> keyListeners = new HashMap<>();

    protected final void listenKey(int key, KeyListener listener) {
        keyListeners.put(key, listener);
    }

    public void notifyCommandListeners(int key) {
        KeyListener listener = keyListeners.get(key);
        if (listener != null) {
            listener.invoke();
        }
    }

    protected interface KeyListener {

        void invoke();
    }
}
