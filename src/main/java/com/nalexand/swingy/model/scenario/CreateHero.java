package com.nalexand.swingy.model.scenario;

import com.nalexand.swingy.controller.CreateHeroController;
import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.model.ModelFacade;

public class CreateHero extends BaseScenarioStep implements CreateHeroController {

    public CreateHero(ModelFacade model) {
        super(model);
    }

    @Override
    public void createHero(Hero hero) {
        Hero currentlySelectedHero = model.getSelectedHero();
        if (currentlySelectedHero != null && hero.type == currentlySelectedHero.type) {
            model.satisfyHero();
        }
        model.setSelectedHero(hero.type);
        model.nextStep(new GameProcess(model));
    }
}
