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

    private static View currentView = null;

    private static GuiView guiView = new GuiView();

    public static void main(String[] args) {
        boolean isValid = args.length == 1 && args[0].matches("(console|gui)");

        if (!isValid) {
            System.out.println("Expected args:\n  console: console mode\n  gui: window mode");
            System.exit(1);
        }

        System.out.println("Swingy!");

        switch (args[0]) {
            case "console":
                currentView = new ConsoleView();
                break;
            case "gui":
                currentView = guiView;
                break;
        }
        currentView.start(model);
        DEBUG_runBattleProcess();
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

        hero.hitPoints = 42;
        hero.currentHitPoints = 42;
        hero.attack = 1;

        mob.hitPoints = 42;
        mob.currentHitPoints = 42;
        mob.attack = 1;
        mob.dropItem(mob.getItem());

        model.startBattle(new Battle(hero, mob, 0, 0));
        model.nextStep(new BattleProcess(model));
    }

    public static void switchView() {
        currentView.stop();
        if (currentView.getName().equals(GuiView.NAME)) {
            currentView = new ConsoleView();
        } else {
            currentView = guiView;
        }
        currentView.start(model);
    }
}
