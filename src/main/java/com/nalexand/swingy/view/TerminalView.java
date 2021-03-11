package com.nalexand.swingy.view;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class TerminalView implements View {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void process() {

        String line = null;

        do {
            try {
                line = scanner.nextLine();
                System.out.println(line);
            } catch (NoSuchElementException | IllegalStateException e) {
                close();
            }
        } while (line != null);
    }

    @Override
    public void close() {
        System.exit(0);
    }
}
