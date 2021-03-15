package com.nalexand.swingy.model.scenario;

import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.base.BaseScenarioStep;
import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.ui.Command;

import java.util.List;

public class Welcome extends BaseScenarioStep {

    public Welcome(ModelFacade model) {
        super(model);

    }

    @Override
    public void resolve(Command command) {
        List<Hero> createdHeroes = model.getCreatedHeroes();
        switch (command) {
            case KEY_1:
                model.nextStep(new CreateHero(model));
                return;
            case KEY_2:
                model.setSelectedHero(createdHeroes.get(0).type);
                break;
            case KEY_3:
                model.setSelectedHero(createdHeroes.get(1).type);
                break;
            case KEY_4:
                model.setSelectedHero(createdHeroes.get(2).type);
                break;
            case KEY_5:
                model.setSelectedHero(createdHeroes.get(3).type);
            case UNKNOWN:
                return;
        }
        model.nextStep(new GameProcess(model));
    }
}
