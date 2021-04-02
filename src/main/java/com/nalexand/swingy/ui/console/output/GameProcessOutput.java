package com.nalexand.swingy.ui.console.output;

import com.nalexand.swingy.controller.GameProcessController;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.models.Cell;
import com.nalexand.swingy.model.models.Hero;
import com.nalexand.swingy.model.models.WorldMap;
import com.nalexand.swingy.model.models.items.Item;
import com.nalexand.swingy.ui.console.BaseConsoleOutput;
import com.nalexand.swingy.ui.console.Command;
import com.nalexand.swingy.utils.Colors;

import java.util.ArrayList;
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

        List<List<Cell>> cellsSpilt = new ArrayList<>();
        for (int row = 0; row < worldMap.getSize(); row++) {
            int startIndex = row * worldMap.getSize();
            cellsSpilt.add(
                    worldMap.getCells().subList(startIndex, startIndex + worldMap.getSize())
            );
        }
        Iterator<List<Cell>> worldMapIterator = cellsSpilt.iterator();

        printLineWithMap(hero.name + ":", worldMapIterator);

        printLineWithMap(String.format(
                "LEVEL: %s",
                hero.level
        ), worldMapIterator);

        printLineWithMap(String.format(
                "XP: %s/%s",
                hero.xp,
                hero.levelThreshold
        ), worldMapIterator);

        printLineWithMap(String.format(
                "HP: %s/%s",
                hero.currentHitPoints,
                hero.getMaxHitPoints()
        ), worldMapIterator);

        printLineWithMap(String.format(
                "ATTACK: %s",
                hero.getAttack()
        ), worldMapIterator);

        printLineWithMap(String.format(
                "DEFENCE: %s",
                hero.getDefence()
        ), worldMapIterator);

        printLineWithMap("ITEMS:", worldMapIterator);
        printLineWithMap(Item.safeFormatItem(hero.helmet, Item.Type.HELMET), worldMapIterator);
        printLineWithMap(Item.safeFormatItem(hero.armor, Item.Type.ARMOR), worldMapIterator);
        printLineWithMap(Item.safeFormatItem(hero.weapon, Item.Type.WEAPON), worldMapIterator);

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
                "%-40s%s\n",
                info,
                nextWorldLineAsString(worldMapIterator)
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
            return Colors.GREEN + "[H]" + Colors.END;
        } else if (cell.isObstacle) {
            return Colors.BLUE + "[0]" + Colors.END;
        } else if (cell.withMob) {
            return Colors.RED + "[X]" + Colors.END;
        } else if (cell.battle != null) {
            return Colors.YELLOW + "[L]" + Colors.END;
        } else {
            return "[.]";
        }
    }
}
