package com.nalexand.swingy.model.scenario;

import com.nalexand.swingy.controller.GameProcessController;
import com.nalexand.swingy.model.*;

public class GameProcess extends BaseScenarioStep implements GameProcessController {

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
    public void moveHero(int xOffset, int yOffset) {
        Hero hero = model.getSelectedHero();
        WorldMap worldMap = hero.worldMap;

        int toPosX = hero.posX + xOffset;
        int toPosY = hero.posY + yOffset;

        boolean isMapEdge = toPosX < 0 || toPosX >= worldMap.getSize() ||
                toPosY < 0 || toPosY >= worldMap.getSize();
        if (isMapEdge) {
            if (hero.currentHitPoints != hero.getMaxHitPoints()) {
                hero.currentHitPoints++;
            }
            model.calculateWorldMap();
        } else  {
            Cell destinationCell = worldMap.getCells().get(toPosY).get(toPosX);
            if (destinationCell.isFree()) {
                model.moveHero(toPosX, toPosY);
            } else if (destinationCell.withMob) {
                Hero mob = model.getMobWithPosition(toPosX, toPosY);
                destinationCell.battle = new Battle(hero, mob, toPosX, toPosY);
                model.startBattle(destinationCell.battle);
                model.nextStep(new BattleProcess.Confirmation(model));
                return;
            } else if (destinationCell.battle != null) {
                hero.battle = destinationCell.battle;
                model.moveHero(toPosX, toPosY);
                model.nextStep(new BattleProcess.Win(model));
                return;
            }
        }
        model.render();
    }
}
