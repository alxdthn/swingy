package com.nalexand.swingy.model.scenario;

import com.nalexand.swingy.model.*;
import com.nalexand.swingy.ui.Command;

public class GameProcess extends BaseScenarioStep {

    protected GameProcess(ModelFacade model) {
        super(model);
        if (model.getSelectedHero().worldMap == null) {
            model.calculateWorldMap();
        }
    }

    @Override
    public void onRendered() {
        Battle battle = model.getBattle();
        if (battle != null) {
            switch (battle.status) {
                case CONFIRMATION:
                    model.nextStep(new BattleProcess.Confirmation(model));
                    break;
                case WIN:
                    model.nextStep(new BattleProcess.Win(model));
                    break;
            }
        }
    }

    @Override
    public void resolve(Command command) {
        Hero hero = model.getSelectedHero();
        switch (command) {
            case KEY_W:
                moveHero(hero.posX,hero.posY - 1);
                break;
            case KEY_A:
                moveHero(hero.posX - 1, hero.posY);
                break;
            case KEY_S:
                moveHero(hero.posX, hero.posY + 1);
                break;
            case KEY_D:
                moveHero(hero.posX + 1, hero.posY);
        }
    }

    private void moveHero(int toPosX, int toPosY) {
        Hero hero = model.getSelectedHero();
        WorldMap worldMap = hero.worldMap;

        boolean isMapEdge = toPosX < 0 || toPosX >= worldMap.getSize() ||
                toPosY < 0 || toPosY >= worldMap.getSize();
        if (isMapEdge) {
            model.calculateWorldMap();
        } else  {
            Cell destinationCell = worldMap.getCells().get(toPosY).get(toPosX);
            if (destinationCell.isFree()) {
                model.moveHero(toPosX, toPosY);
            } else if (destinationCell.withMob) {
                Hero mob = model.getMobWithPosition(toPosX, toPosY);
                model.startBattle(new Battle(hero, mob));
                model.nextStep(new BattleProcess.Confirmation(model));
                return;
            }
        }
        model.render();
    }
}
