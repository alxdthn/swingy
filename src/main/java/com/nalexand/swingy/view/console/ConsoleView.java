package com.nalexand.swingy.view.console;

import com.nalexand.swingy.view.View;

public class ConsoleView implements View {

    @Override
    public void showWelcome() {
        System.out.println("Console mode activated!");
        System.out.println("1: Create a hero");
        System.out.println("2: Select a previously created hero");
    }
}
