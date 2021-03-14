package com.nalexand.swingy.model.scenario;

import com.nalexand.swingy.model.WorldMap;
import com.nalexand.swingy.model.base.BaseScenarioStep;
import com.nalexand.swingy.model.base.ScenarioStep;
import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.ui.Command;
import com.nalexand.swingy.utils.Utils;

public class GameProcess extends BaseScenarioStep<CreateHero> implements ScenarioStep.Data {

    private final Hero hero;

    protected GameProcess(ModelFacade model) {
        super(model);
        hero = model.getGameState().getSelectedHero();
        hero.prepareToGame();
        Utils.saveGameState(model.getGameState());
    }

    @Override
    public void resolve(Command command) {

    }

    public Hero getHero() {
        return hero;
    }
}
