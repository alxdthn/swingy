package com.nalexand.swingy.ui.console;

import com.nalexand.swingy.controller.*;
import com.nalexand.swingy.model.*;
import com.nalexand.swingy.model.items.Item;
import com.nalexand.swingy.model.scenario.BaseScenarioStep;
import com.nalexand.swingy.ui.base.BaseView;
import com.nalexand.swingy.utils.Colors;

import java.util.*;
import java.util.stream.Collectors;

import static com.nalexand.swingy.utils.Utils.*;

public final class ConsoleView extends BaseView {

    private final Scanner scanner = new Scanner(System.in);

    private Map<Command, CommandListener> keyListeners;

    @Override
    public void renderScenarioData(BaseScenarioStep scenarioStep) {
        keyListeners = new HashMap<>();
        super.renderScenarioData(scenarioStep);
    }

    @Override
    protected void showWelcome(ModelFacade model, WelcomeController controller) {
        printDash();

        println("Select hero");
        println("1: Create a hero");
        listenCommand(Command.KEY_1, controller::showCreateHero);

        int option = 2;
        Command[] options = {Command.KEY_2, Command.KEY_3, Command.KEY_4, Command.KEY_5};
        for (Hero hero : model.getCreatedHeroes()) {
            printFormat("%d: %s\n", option++, hero.name);
            listenCommand(options[++option - 1], () -> controller.selectHeroAndShowGameProcess(hero));
        }
    }

    @Override
    protected void showCreateHero(ModelFacade model, CreateHeroController controller) {
        printDash();

        println("Create hero");

        int option = 1;
        Command[] options = {Command.KEY_1, Command.KEY_2, Command.KEY_3, Command.KEY_4};
        for (Hero hero : model.getAvailableForCreateHeroes()) {
            printFormat("%d: %s\n", option, hero.name);
            listenCommand(options[option - 1], () -> controller.createHero(hero));
            option++;
        }
    }

    @Override
    protected void showGameProcess(ModelFacade model, GameProcessController controller) {
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

        listenCommand(Command.KEY_W, () -> controller.moveHero(0, -1));
        listenCommand(Command.KEY_A, () -> controller.moveHero(-1, 0));
        listenCommand(Command.KEY_S, () -> controller.moveHero(0, 1));
        listenCommand(Command.KEY_D, () -> controller.moveHero(1, 0));
    }

    @Override
    protected void showBattleConfirmation(ModelFacade model, DialogController controller) {
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

        listenCommand(Command.KEY_1, controller::accept);
        listenCommand(Command.KEY_2, controller::dismiss);
    }

    @Override
    protected void showBattle(ModelFacade model) {
        printDash();

        Battle battle = model.getBattle();
        Hero hero = model.getSelectedHero();

        String heroTitle = String.format("%s (%d hp)", hero.name, battle.heroStartHp);
        String mobTitle = String.format("%s (%d hp)", battle.mob.name, battle.mobStartHp);

        printFormat("%34s\n", "BATTLE!");
        printFormat("%28s  |  %s\n", heroTitle, mobTitle);
        printFormat("%-8s|%8s|%12s|%-12s|%-8s\n", "step", "hp", "damage", "damage", "hp");
        battle.getSteps().forEach(step -> println(step.format()));
    }

    @Override
    protected void showBattleWin(ModelFacade model, BattleWinController controller) {
        printDash();

        Battle battle = model.getBattle();

        println("You are win!");
        List<Item> drop = listOfNotNull(
                battle.mob.helmet,
                battle.mob.armor,
                battle.mob.weapon
        );
        if (!drop.isEmpty()) {
            println("Drop:");
            int option = 1;
            for (Item item : drop) {
                printFormat("%d: %s", option++, item.getFormattedString());
            }
        }
        println("1: Ok!");
        listenCommand(Command.KEY_1, controller::accept);
    }

    @Override
    protected void showBattleLose(ModelFacade model, DialogController controller) {
        printDash();

        println("You are lose :(");
        println("1: Fuck!");
        listenCommand(Command.KEY_1, controller::accept);
    }

    private void printDash() {
        println("------------------------------------------------------------------");
    }

    private void printMapLine(Iterator<List<Cell>> worldMapIterator) {
        printLineWithMap("", worldMapIterator);
    }

    private void printLineWithMap(String info, Iterator<List<Cell>> worldMapIterator) {
        printFormat(
                "%-32s%s\n",
                info,
                nextWorldLineAsString(worldMapIterator)
        );
    }

    private void printLineWithMap(String info, Iterator<List<Cell>> worldMapIterator, String itemLine) {
        printFormat(
                "%-32s%s %s\n",
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

    public void start() {
        String line;
        while ((line = getLine()) != null) {
            Command command = getCommand(line);
            CommandListener listener = keyListeners.get(command);
            if (listener != null) {
                listener.invoke();
            }
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

    private void listenCommand(Command command, CommandListener listener) {
        keyListeners.put(command, listener);
    }

    private interface CommandListener {

        void invoke();
    }

    private enum Command {
        KEY_1,
        KEY_2,
        KEY_3,
        KEY_4,
        KEY_5,
        KEY_W,
        KEY_A,
        KEY_S,
        KEY_D,
        UNKNOWN
    }
}
