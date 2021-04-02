package com.nalexand.swingy.model;

import com.nalexand.swingy.Swingy;
import com.nalexand.swingy.model.database.DataBaseInteractor;
import com.nalexand.swingy.model.file.FileInteractor;
import com.nalexand.swingy.model.scenario.BaseScenarioStep;
import com.nalexand.swingy.model.scenario.Welcome;
import com.nalexand.swingy.model.validator.GameStateValidator;
import com.nalexand.swingy.ui.View;
import com.nalexand.swingy.utils.GameLogics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModelFacade {

    private View view = null;

    private FileInteractor fileInteractor;

    private DataBaseInteractor dataBaseInteractor;

    private GameState gameState;

    private BaseScenarioStep currentStep = new Welcome(this);

    public ModelFacade() {
        switch (Swingy.SAVE_METHOD) {
            case DATA_BASE:
                dataBaseInteractor = new DataBaseInteractor();
                gameState = dataBaseInteractor.loadGameState();
                break;
            case FILE:
                fileInteractor = new FileInteractor();
                gameState = fileInteractor.loadGameState();
                break;
        }
        gameState = new GameStateValidator().validate(gameState);
    }

    //region Process
    public void render() {
        view.renderScenarioData(currentStep);
        currentStep.onRendered();
    }

    public void nextStep(BaseScenarioStep scenarioStep) {
        currentStep = scenarioStep;
        render();
    }
    //endregion

    //region Data providing
    public Hero getSelectedHero() {
        if (gameState.selectedHeroType == null) return null;
        return gameState.heroes.get(gameState.selectedHeroType);
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
        return getSelectedHero().battle;
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
        Hero.Type heroType = currentHero.type;
        gameState.heroes.put(currentHero.type, new Hero(currentHero.type));
        gameState.selectedHeroType = null;
        saveGameState(heroType);
    }
    //endregion

    //region State interactions
    public void setSelectedHero(Hero selectedHero, boolean createNew) {
        Hero currentHero = getSelectedHero();
        if (createNew) {
            currentHero = new Hero(selectedHero.type);
            GameLogics.initAsHero(currentHero);
            gameState.heroes.put(selectedHero.type, currentHero);
        } else if (currentHero != null) {
            currentHero.selected = false;
        }
        gameState.selectedHeroType = selectedHero.type;
        selectedHero.selected = true;
        GameLogics.initAsHero(selectedHero);
    }

    public void calculateWorldMap() {
        Hero selectedHero = getSelectedHero();
        selectedHero.worldMap = new WorldMap();
        selectedHero.worldMap.generateWorld(selectedHero);
    }

    public void moveHeroToMob() {
        moveHero(getBattle().mob.posX, getBattle().mob.posY);
    }

    public void moveHero(int toPosX, int toPosY) {
        Hero hero = getSelectedHero();
        Cell destinationCell = hero.worldMap.getCell(toPosX, toPosY);

        hero.worldMap.getCell(hero.posX, hero.posY).withHero = false;
        hero.posX = toPosX;
        hero.posY = toPosY;
        destinationCell.withHero = true;
        destinationCell.withMob = false;
    }

    public void startBattle(Battle battle) {
        getSelectedHero().battle = battle;
    }

    public void clearBattle() {
        Hero selectedHero = getSelectedHero();
        selectedHero.battle = null;
    }

    public void saveGameState() {
        saveGameState(gameState.selectedHeroType);
    }

    private void saveGameState(Hero.Type heroType) {
        switch (Swingy.SAVE_METHOD) {
            case DATA_BASE:
                dataBaseInteractor.saveGameState(gameState, heroType);
                break;
            case FILE:
                fileInteractor.saveGameState(gameState);
                break;
        }
    }
    //endregion

    public void setView(View view) {
        this.view = view;
    }
}
