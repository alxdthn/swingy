package com.nalexand.swingy.ui.console;

import com.nalexand.swingy.model.WorldMap;
import com.nalexand.swingy.model.scenario.CreateHero;
import com.nalexand.swingy.model.scenario.GameProcess;
import com.nalexand.swingy.model.scenario.Welcome;
import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.ui.base.BaseView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static com.nalexand.swingy.utils.Utils.printFormat;
import static com.nalexand.swingy.utils.Utils.println;

public final class ConsoleView extends BaseView {

    private static final int INFO_WIDTH = 42;

    @Override
    protected void showWelcome(Welcome welcome) {
        printDash();

        println("Select hero");
        println("1: Create a hero");

        int option = 2;
        for (Hero hero : welcome.createdHeroes) {
            printFormat("%d: %s\n", option++, hero.name);
        }
    }

    @Override
    protected void showCreateHero(CreateHero createHero) {
        printDash();

        println("Create hero");

        int option = 1;
        for (Hero hero : createHero.getAvailableHeroes()) {
            printFormat("%d: %s\n", option++, hero.name);
        }
    }

    @Override
    protected void showGameProcess(GameProcess gameProcess) {
        printDash();

        Hero hero = gameProcess.getHero();
        WorldMap worldMap = hero.getWorldMap();

        Iterator<List<WorldMap.Cell>> worldMapIterator = worldMap.getCells().iterator();

        printLine(hero.name + ":", worldMapIterator);

        printLine(String.format(
                "level: %s",
                hero.getLevel()
        ), worldMapIterator);

        printLine(String.format(
                "xp: %s",
                hero.getXp()
        ), worldMapIterator);

        printLine(String.format(
                "xp: %s",
                hero.getXp()
        ), worldMapIterator);

        printLine(String.format(
                "hp: %s",
                hero.getHp()
        ), worldMapIterator);

        printLine(String.format(
                "attack: %s",
                hero.getAttack()
        ), worldMapIterator);

        printLine(String.format(
                "defence: %s",
                hero.getDefence()
        ), worldMapIterator);

        printLine(String.format(
                "hit points: %s",
                hero.getHitPoints()
        ), worldMapIterator);

        while (worldMapIterator.hasNext()) {
            printLine(worldMapIterator);
        }
    }

    private void printDash() {
        println("------------------------------------------------------------------");
    }

    private void printLine(Iterator<List<WorldMap.Cell>> worldMapIterator) {
        printLine("", worldMapIterator);
    }

    private void printLine(String info, Iterator<List<WorldMap.Cell>> worldMapIterator) {
        printFormat(
                "%-" + INFO_WIDTH + "s%s\n",
                info,
                nextWorldLineAsString(worldMapIterator)
        );
    }

    private String nextWorldLineAsString(Iterator<List<WorldMap.Cell>> worldMapIterator) {
        return worldMapIterator
                .next()
                .stream()
                .map(WorldMap.Cell::toString)
                .collect(Collectors.joining());
    }
}
