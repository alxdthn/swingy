package com.nalexand.swingy.model.scenario;

import com.nalexand.swingy.controller.WelcomeController;
import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.model.ModelFacade;

public class Welcome extends BaseScenarioStep implements WelcomeController {

    public Welcome(ModelFacade model) {
        super(model);
    }

    @Override
    public void showCreateHero() {
        model.nextStep(new CreateHero(model));
    }

    @Override
    public void selectHeroAndShowGameProcess(Hero hero) {
        model.setSelectedHero(hero, false);
        model.saveGameState();
        model.nextStep(new GameProcess(model));
    }
}
