package com.nalexand.swingy.model;

import com.nalexand.swingy.model.*;
import com.nalexand.swingy.model.base.BaseScenarioStep;
import com.nalexand.swingy.model.file.FileInteractor;
import com.nalexand.swingy.model.scenario.Welcome;
import com.nalexand.swingy.ui.Command;
import com.nalexand.swingy.ui.base.View;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModelFacade {

    private View view = null;

    private FileInteractor fileInteractor = new FileInteractor();

    private GameState gameState = fileInteractor.loadGameState();

    private BaseScenarioStep currentStep = new Welcome(this);

    //region Process
    public void render() {
        view.renderScenarioData(currentStep);
        currentStep.onRendered();
    }

    public void resolveCommand(Command command) {
        currentStep.resolve(command);
    }

    public void nextStep(BaseScenarioStep scenarioStep) {
        currentStep = scenarioStep;
        render();
    }
    //endregion

    //region Data providing
    public Hero getSelectedHero() {
        if (gameState.selectedHero == null) return null;
        return gameState.heroes.get(gameState.selectedHero);
    }

    public List<Hero> getCreatedHeroes() {
        return gameState.heroes
                .values()
                .stream()
                .filter(hero -> hero.created)
                .collect(Collectors.toList());
    }

    public List<Hero> getAvailableForCreateHeroes() {
        return new ArrayList<>(gameState.heroes.values());
    }

    public Battle getBattle() {
        return gameState.battle;
    }

    public Hero getMobWithPosition(int posX, int posY) {
        List<Hero> mobs = getSelectedHero().worldMap.mobs;
        for (Hero mob : mobs) {
            if (mob.posX == posX && mob.posY == posY) {
                return mob;
            }
        }
        throw new IllegalStateException(String.format("No mob with x=%d; y=%d", posX, posY));
    }

    public void satisfyHero() {
        Hero currentHero = getSelectedHero();
        gameState.heroes.put(currentHero.type, new Hero(currentHero.type));
        gameState.selectedHero = null;
        saveGameState();
    }
    //endregion

    //region State interactions
    public void setSelectedHero(Hero.Type selectedHeroType) {
        gameState.selectedHero = selectedHeroType;
        getSelectedHero().created = true;
        saveGameState();
    }

    public void calculateWorldMap() {
        Hero selectedHero = getSelectedHero();
        selectedHero.worldMap = new WorldMap();
        selectedHero.worldMap.generateWorld(selectedHero);
        saveGameState();
    }

    public void moveHeroToMob() {
        moveHero(getBattle().mob.posX, getBattle().mob.posY);
    }

    public void moveHero(int toPosX, int toPosY) {
        Hero hero = getSelectedHero();
        Cell destinationCell = hero.worldMap.getCells().get(toPosY).get(toPosX);

        hero.worldMap.getCells().get(hero.posY).get(hero.posX).withHero = false;
        hero.posX = toPosX;
        hero.posY = toPosY;
        destinationCell.withHero = true;
        destinationCell.withMob = false;
        saveGameState();
    }


    public void startBattle(Battle battle) {
        gameState.battle = battle;
        saveGameState();
    }

    public void increaseExperience(int xp) {
        getSelectedHero().increaseExperience(xp);
        saveGameState();
    }

    public void clearBattle() {
        gameState.battle = null;
        saveGameState();
    }

    public void removeBattleMob() {
        getSelectedHero().worldMap.removeMob(getBattle().mob);
    }

    public void saveGameState() {
        fileInteractor.saveGameState(gameState);
    }
    //endregion

    public void setView(View view) {
        this.view = view;
    }
}
