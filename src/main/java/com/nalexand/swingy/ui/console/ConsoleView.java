package com.nalexand.swingy.ui.console;

import com.nalexand.swingy.model.*;
import com.nalexand.swingy.model.items.Item;
import com.nalexand.swingy.ui.Command;
import com.nalexand.swingy.ui.base.BaseView;
import com.nalexand.swingy.utils.Colors;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.nalexand.swingy.utils.Utils.*;

public final class ConsoleView extends BaseView {

    private final Scanner scanner = new Scanner(System.in);

    private static final int INFO_WIDTH = 32;

    @Override
    protected void showWelcome(ModelFacade model) {
        printDash();

        println("Select hero");
        println("1: Create a hero");

        int option = 2;
        for (Hero hero : model.getCreatedHeroes()) {
            printFormat("%d: %s\n", option++, hero.name);
        }
    }

    @Override
    protected void showCreateHero(ModelFacade model) {
        printDash();

        println("Create hero");

        int option = 1;
        for (Hero hero : model.getAvailableForCreateHeroes()) {
            printFormat("%d: %s\n", option++, hero.name);
        }
    }

    @Override
    protected void showGameProcess(ModelFacade model) {
        printDash();

        Hero hero = model.getSelectedHero();
        WorldMap worldMap = hero.worldMap;

        Iterator<List<Cell>> worldMapIterator = worldMap.getCells().iterator();

        printLineWithMap(hero.name + ":", worldMapIterator, "Items:");

        printLineWithMap(String.format(
                "LEVEL: %s",
                hero.getLevel()
        ), worldMapIterator, Item.safeFormatItem(hero.helmet));

        printLineWithMap(String.format(
                "XP: %s",
                hero.getXp()
        ), worldMapIterator, Item.safeFormatItem(hero.armor));

        printLineWithMap(String.format(
                "HP: %s",
                hero.getHitPoints()
        ), worldMapIterator, Item.safeFormatItem(hero.weapon));

        printLineWithMap(String.format(
                "ATTACK: %s",
                hero.getAttack()
        ), worldMapIterator);

        printLineWithMap(String.format(
                "DEFENCE: %s",
                hero.getDefence()
        ), worldMapIterator);

        while (worldMapIterator.hasNext()) {
            printMapLine(worldMapIterator);
        }
    }

    @Override
    protected void showBattleConfirmation(ModelFacade model) {
        printDash();

        Battle battle = model.getBattle();
        Hero mob = battle.mob;
        printFormat("You are meet with %s\n", mob.name);
        printFormat("level %s\n", mob.level);
        println("Items:");
        listOfNotNull(
                mob.helmet,
                mob.armor,
                mob.weapon
        ).forEach(item -> println(item.getFormattedString()));

        println("1: Fight");
        println("2: Run");
    }

    @Override
    protected void showBattle(ModelFacade model) {
        printDash();

        Battle battle = model.getBattle();

        printFormat("Battle with %s!\n", battle.mob.name);
        println("");
        println("1: Win");
        println("2: Lose");
    }

    @Override
    protected void showBattleWin(ModelFacade model) {
        printDash();

        println("You are win!");
        println("1: Ok!");
    }

    @Override
    protected void showBattleLose(ModelFacade model) {
        printDash();

        println("You are lose :(");
        println("1: Fuck!");
    }

    private void printDash() {
        println("------------------------------------------------------------------");
    }

    private void printMapLine(Iterator<List<Cell>> worldMapIterator) {
        printLineWithMap("", worldMapIterator);
    }

    private void printLineWithMap(String info, Iterator<List<Cell>> worldMapIterator) {
        printFormat(
                "%-" + INFO_WIDTH + "s%s\n",
                info,
                nextWorldLineAsString(worldMapIterator)
        );
    }

    private void printLineWithMap(String info, Iterator<List<Cell>> worldMapIterator, String itemLine) {
        printFormat(
                "%-" + INFO_WIDTH + "s%s %s\n",
                info,
                nextWorldLineAsString(worldMapIterator),
                itemLine
        );
    }

    private String nextWorldLineAsString(Iterator<List<Cell>> worldMapIterator) {
        if (!worldMapIterator.hasNext()) return "";
        return worldMapIterator
                .next()
                .stream()
                .map(this::printCell)
                .collect(Collectors.joining());
    }

    private String printCell(Cell cell) {
        if (cell.withHero) {
            return Colors.YELLOW + "[H]" + Colors.END;
        } else if (cell.isObstacle) {
            return Colors.GREEN + "[X]" + Colors.END;
        } else if (cell.withMob) {
            return Colors.RED + "[M]" + Colors.END;
        } else {
            return "[.]";
        }
    }

    public void start(ModelFacade model) {
        String line;
        while ((line = getLine()) != null) {
            Command command = getCommand(line);
            model.resolveCommand(command);
        }
    }

    private String getLine() {
        String result = null;

        try {
            result = scanner.nextLine();
        } catch (NoSuchElementException | IllegalStateException e) {
            System.exit(1);
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
        }
        return result;
    }
}
