package com.nalexand.swingy.model.scenario;

import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.ui.Command;

import java.util.List;

public class CreateHero extends BaseScenarioStep {

    private List<Hero> availableHeroes;

    public CreateHero(ModelFacade model) {
        super(model);
        availableHeroes = model.getAvailableForCreateHeroes();
    }

    @Override
    public void resolve(Command command) {
        Hero selectedHero;
        switch (command) {
            case KEY_1:
                selectedHero = availableHeroes.get(0);
                break;
            case KEY_2:
                selectedHero = availableHeroes.get(1);
                break;
            case KEY_3:
                selectedHero = availableHeroes.get(2);
                break;
            case KEY_4:
                selectedHero = availableHeroes.get(3);
                break;
            default:
                return;
        }
        Hero currentlySelectedHero = model.getSelectedHero();
        if (currentlySelectedHero != null && selectedHero.type == currentlySelectedHero.type) {
            model.satisfyHero();
        }
        model.setSelectedHero(selectedHero.type);
        model.nextStep(new GameProcess(model));
    }
}
