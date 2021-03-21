package com.nalexand.swingy.ui.base;

import com.nalexand.swingy.ui.console.Command;

import java.util.HashMap;
import java.util.Map;

public class BaseConsoleOutput {

    private Map<Command, CommandListener> commandListeners = new HashMap<>();

    protected final void listenCommand(Command command, CommandListener listener) {
        commandListeners.put(command, listener);
    }

    public void notifyCommandListeners(Command command) {
        CommandListener listener = commandListeners.get(command);
        if (listener != null) {
            listener.invoke();
        }
    }

    protected interface CommandListener {

        void invoke();
    }
}
