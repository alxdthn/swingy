package com.nalexand.swingy.model.scenario;

import com.nalexand.swingy.controller.CreateHeroController;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.models.Hero;

public class CreateHero extends BaseScenarioStep implements CreateHeroController {

    public CreateHero(ModelFacade model) {
        super(model);
    }

    @Override
    public void createHero(Hero hero) {
        model.setSelectedHero(hero, true);
        model.saveGameState();
        model.nextStep(new GameProcess(model));
    }
}
