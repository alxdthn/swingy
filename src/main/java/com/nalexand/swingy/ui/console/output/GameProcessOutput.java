package com.nalexand.swingy.ui.console.output;

import com.nalexand.swingy.controller.GameProcessController;
import com.nalexand.swingy.model.Cell;
import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.WorldMap;
import com.nalexand.swingy.model.items.Item;
import com.nalexand.swingy.ui.base.BaseConsoleOutput;
import com.nalexand.swingy.ui.console.Command;
import com.nalexand.swingy.utils.Colors;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.nalexand.swingy.ui.console.output.OutputUtils.printDash;
import static com.nalexand.swingy.utils.Utils.printFormat;

public class GameProcessOutput extends BaseConsoleOutput {

    public GameProcessOutput(ModelFacade model, GameProcessController controller) {
        printDash();

        Hero hero = model.getSelectedHero();
        WorldMap worldMap = hero.worldMap;

        Iterator<List<Cell>> worldMapIterator = worldMap.getCells().iterator();

        printLineWithMap(hero.name + ":", worldMapIterator, "Items:");

        printLineWithMap(String.format(
                "LEVEL: %s",
                hero.getLevel()
        ), worldMapIterator, Item.safeFormatItem(hero.helmet, Item.Type.HELMET));

        printLineWithMap(String.format(
                "XP: %s",
                hero.getXp()
        ), worldMapIterator, Item.safeFormatItem(hero.armor, Item.Type.ARMOR));

        printLineWithMap(String.format(
                "HP: %s/%s",
                hero.currentHitPoints,
                hero.getHitPoints()
        ), worldMapIterator, Item.safeFormatItem(hero.weapon, Item.Type.WEAPON));

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

        listenCommand(Command.KEY_W, () -> controller.moveHero(0, -1));
        listenCommand(Command.KEY_A, () -> controller.moveHero(-1, 0));
        listenCommand(Command.KEY_S, () -> controller.moveHero(0, 1));
        listenCommand(Command.KEY_D, () -> controller.moveHero(1, 0));
    }

    private void printMapLine(Iterator<List<Cell>> worldMapIterator) {
        printLineWithMap("", worldMapIterator);
    }

    private void printLineWithMap(String info, Iterator<List<Cell>> worldMapIterator) {
        printFormat(
                "%-20s%s\n",
                info,
                nextWorldLineAsString(worldMapIterator)
        );
    }

    private void printLineWithMap(String info, Iterator<List<Cell>> worldMapIterator, String itemLine) {
        printFormat(
                "%-20s%s %s\n",
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
}
