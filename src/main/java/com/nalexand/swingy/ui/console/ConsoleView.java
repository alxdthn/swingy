package com.nalexand.swingy.ui.console;

import com.nalexand.swingy.model.Battle;
import com.nalexand.swingy.model.Cell;
import com.nalexand.swingy.model.WorldMap;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.ui.base.BaseView;
import com.nalexand.swingy.utils.Colors;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.nalexand.swingy.utils.Utils.printFormat;
import static com.nalexand.swingy.utils.Utils.println;

public final class ConsoleView extends BaseView {

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

        printLineWithMap(hero.name + ":", worldMapIterator);

        printLineWithMap(String.format(
                "level: %s",
                hero.getLevel()
        ), worldMapIterator);

        printLineWithMap(String.format(
                "xp: %s",
                hero.getXp()
        ), worldMapIterator);

        printLineWithMap(String.format(
                "xp: %s",
                hero.getXp()
        ), worldMapIterator);

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
