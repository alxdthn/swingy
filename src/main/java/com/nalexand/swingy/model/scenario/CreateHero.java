package com.nalexand.swingy.model.scenario;

import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.model.base.BaseScenarioStep;
import com.nalexand.swingy.model.base.ScenarioStep;
import com.nalexand.swingy.ui.Command;

import java.util.List;

public class CreateHero extends BaseScenarioStep<CreateHero> implements ScenarioStep.Data {

    private List<Hero> availableHeroes;

    public CreateHero(ModelFacade model) {
        super(model);
        availableHeroes = model.getGameState().getAvailableForCreateHeroes();
    }

    @Override
    public void resolve(Command command) {
        Hero selectedHero = null;
        switch (command) {
            case OPTION_1:
                selectedHero = availableHeroes.get(0);
                break;
            case OPTION_2:
                selectedHero = availableHeroes.get(1);
                break;
            case OPTION_3:
                selectedHero = availableHeroes.get(2);
                break;
            case OPTION_4:
                selectedHero = availableHeroes.get(3);
                break;
            case UNKNOWN:
                break;
        }
        if (selectedHero != null) {
            if (selectedHero.equals(model.getGameState().getSelectedHero())) {
                //model.nextStep(new Dialog(model));
            } else {
                model.getGameState().setSelectedHero(selectedHero.type);
                model.nextStep(new GameProcess(model));
            }
        }
    }

    public List<Hero> getAvailableHeroes() {
        return availableHeroes;
    }
}
