package com.nalexand.swingy;

import com.nalexand.swingy.model.Battle;
import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.scenario.BattleProcess;
import com.nalexand.swingy.ui.base.View;
import com.nalexand.swingy.ui.console.ConsoleView;
import com.nalexand.swingy.ui.gui.GuiView;
import com.nalexand.swingy.utils.GameLogics;

public class Swingy {

    public static final String GAME_DATA_FILE = "./game_data.json";

    public static final boolean IGNORE_SAVED = true;

    public static final double OBSTACLES_PERCENTAGE = 0.25;

    public static final int ITEM_GENERATION_PERCENTAGE = 75;

    public static final int INITIAL_HERO_HP = 4;

    public static final String[] MOB_NAMES = {"Ork", "Necromancer", "Bandit", "Wood Elf"};

    private static final ModelFacade model = new ModelFacade();

    private static final ConsoleView consoleView = new ConsoleView();

    private static final GuiView guiView = new GuiView();

    private static View currentView = null;

    public static void main(String[] args) {
        boolean isValid = args.length == 1 && args[0].matches("(console|gui)");

        if (!isValid) {
            System.out.println("Expected args:\n  console: console mode\n  gui: window mode");
            System.exit(1);
        }

        System.out.println("Swingy!");

        switch (args[0]) {
            case "console":
                currentView = consoleView;
                model.setView(consoleView);
                model.render();
                consoleView.start();
                break;
            case "gui":
                currentView = guiView;
                model.setView(guiView);
                guiView.initGui(model);
                model.render();
//                DEBUG_runBattleProcess();
                break;
        }
    }

    private static void DEBUG_runBattleConfirmation() {
        model.setSelectedHero(Hero.Type.URSA);
        Hero hero = model.getSelectedHero();
        model.calculateWorldMap();
        Hero mob = hero.worldMap.mobs.get(0);
        GameLogics.initAsMob(mob, hero);
        model.startBattle(new Battle(hero, mob, 0, 0));
        model.nextStep(new BattleProcess.Confirmation(model));
    }

    private static void DEBUG_runBattleProcess() {
        model.setSelectedHero(Hero.Type.URSA);
        Hero hero = model.getSelectedHero();
        model.calculateWorldMap();
        Hero mob = hero.worldMap.mobs.get(0);
        //GameLogics.initAsMob(mob, hero);

        hero.hitPoints = 12;
        hero.currentHitPoints = 12;
        hero.attack = 1;

        mob.hitPoints = 11;
        mob.attack = 1;
        mob.dropItem(mob.getItem());

        model.startBattle(new Battle(hero, mob, 0, 0));
        model.nextStep(new BattleProcess(model));
    }

    public static void switchView() {
        if (currentView == guiView) {
            model.setView(consoleView);
        } else {
            model.setView(guiView);
        }
        model.render();
    }
}
