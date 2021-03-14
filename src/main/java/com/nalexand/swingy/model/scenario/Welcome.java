package com.nalexand.swingy.model.scenario;

import com.nalexand.swingy.model.base.BaseScenarioStep;
import com.nalexand.swingy.model.base.ScenarioStep;
import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.ui.Command;

import java.util.List;

public class Welcome extends BaseScenarioStep<Welcome> implements ScenarioStep.Data {

    public List<Hero> createdHeroes;

    public Welcome(ModelFacade model) {
        super(model);
        createdHeroes = model.getGameState().getCreatedHeroes();
    }

    @Override
    public void resolve(Command command) {
        switch (command) {
            case OPTION_1:
                model.nextStep(new CreateHero(model));
                return;
            case OPTION_2:
                model.getGameState().setSelectedHero(createdHeroes.get(0).type);
                break;
            case OPTION_3:
                model.getGameState().setSelectedHero(createdHeroes.get(1).type);
                break;
            case OPTION_4:
                model.getGameState().setSelectedHero(createdHeroes.get(2).type);
                break;
            case OPTION_5:
                model.getGameState().setSelectedHero(createdHeroes.get(3).type);
            case UNKNOWN:
                break;
        }
        model.nextStep(new GameProcess(model));
    }
}
