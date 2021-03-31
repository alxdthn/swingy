package com.nalexand.swingy.ui.console;

import com.nalexand.swingy.Swingy;
import com.nalexand.swingy.controller.*;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.ui.base.BaseConsoleOutput;
import com.nalexand.swingy.ui.base.BaseView;
import com.nalexand.swingy.ui.console.output.*;

import java.util.NoSuchElementException;
import java.util.Scanner;

public final class ConsoleView extends BaseView {

    public final static String NAME = "console";

    private final Scanner scanner = new Scanner(System.in);

    private BaseConsoleOutput consoleOutput;

    public boolean isActive = false;

    @Override
    protected void showWelcome(ModelFacade model, WelcomeController controller) {
        consoleOutput = new WelcomeOutput(model, controller);
    }

    @Override
    protected void showCreateHero(ModelFacade model, CreateHeroController controller) {
        consoleOutput = new CreateHeroOutput(model, controller);
    }

    @Override
    protected void showGameProcess(ModelFacade model, GameProcessController controller) {
        consoleOutput = new GameProcessOutput(model, controller);
    }

    @Override
    protected void showBattleConfirmation(ModelFacade model, DialogController controller) {
        consoleOutput = new BattleConfirmationOutput(model, controller);
    }

    @Override
    protected void showBattle(ModelFacade model, BattleController controller) {
        consoleOutput = new BattleOutput(model, controller);
    }

    @Override
    protected void showBattleWin(ModelFacade model, BattleWinController controller) {
        consoleOutput = new BattleWinOutput(model, controller);
    }

    @Override
    protected void showBattleLose(ModelFacade model, DialogController controller) {
        consoleOutput = new BattleLoseOutput(model, controller);
    }

    @Override
    public void start(ModelFacade model) {
        isActive = true;
        model.setView(this);
        model.render();
        String line;
        while ((line = getLine()) != null) {
            Command command = getCommand(line);
            if (command == Command.GUI) {
                Swingy.switchView();
            } else {
                consoleOutput.notifyCommandListeners(command);
            }
            if (!isActive) break;
        }
    }

    @Override
    public void stop() {
        isActive = false;
    }

    @Override
    public String getName() {
        return NAME;
    }

    private String getLine() {
        String result = null;

        try {
            result = scanner.nextLine();
        } catch (NoSuchElementException | IllegalStateException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Command getCommand(String line) {
        Command result = Command.UNKNOWN;

        switch (line) {
            case "1":
                result = Command.KEY_1;
                break;
            case "2":
                result = Command.KEY_2;
                break;
            case "3":
                result = Command.KEY_3;
                break;
            case "4":
                result = Command.KEY_4;
                break;
            case "w":
                result = Command.KEY_W;
                break;
            case "a":
                result = Command.KEY_A;
                break;
            case "s":
                result = Command.KEY_S;
                break;
            case "d":
                result = Command.KEY_D;
                break;
            case "gui":
                result = Command.GUI;
        }
        return result;
    }
}
