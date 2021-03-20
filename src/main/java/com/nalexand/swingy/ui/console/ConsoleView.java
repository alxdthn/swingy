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
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.nalexand.swingy.utils.Utils.printFormat;
import static com.nalexand.swingy.utils.Utils.println;

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
                "level: %s",
                hero.getLevel()
        ), worldMapIterator, formatItem(hero, Item.Type.HELMET));

        printLineWithMap(String.format(
                "xp: %s",
                hero.getXp()
        ), worldMapIterator, formatItem(hero, Item.Type.ARMOR));

        printLineWithMap(String.format(
                "xp: %s",
                hero.getXp()
        ), worldMapIterator, formatItem(hero, Item.Type.WEAPON));

        printLineWithMap(String.format(
                "hp: %s",
                hero.getHp()
        ), worldMapIterator);

        printLineWithMap(String.format(
                "attack: %s",
                hero.getAttack()
        ), worldMapIterator);

        printLineWithMap(String.format(
                "defence: %s",
                hero.getDefence()
        ), worldMapIterator);

        printLineWithMap(String.format(
                "hit points: %s",
                hero.getHitPoints()
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
        println("1: Fight");
        println("2: Run");
    }

    @Override
    protected void showBattle(ModelFacade model) {
        printDash();

        println("Battle!");
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

    private String formatItem(Hero hero, Item.Type type) {
        String itemTypeString;
        String formattedItem;
        Item item;
        switch (type) {
            case WEAPON:
                itemTypeString = "weapon";
                item = hero.weapon;
                formattedItem = safeFormatItem(item, (notNullItem) ->
                        String.format("%s attack = %d", notNullItem.name, notNullItem.attack)
                );
                break;
            case ARMOR:
                itemTypeString = "armor";
                item = hero.armor;
                formattedItem = safeFormatItem(item, (notNullItem) ->
                        String.format("%s defence = %d", notNullItem.name, notNullItem.defence)
                );
                break;
            default:
                itemTypeString = "helmet";
                item = hero.helmet;
                formattedItem = safeFormatItem(item, (notNullItem) ->
                        String.format("%s hitPoints = %d", notNullItem.name, notNullItem.hitPoints)
                );
                break;
        }
        return String.format("%s: %s",
                itemTypeString,
                formattedItem
        );
    }

    private String safeFormatItem(Item item, Function<Item, String> formatter) {
        if (item == null) {
            return "EMPTY";
        } else {
            return formatter.apply(item);
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
