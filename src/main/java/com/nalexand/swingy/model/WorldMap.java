package com.nalexand.swingy.model;

import com.nalexand.swingy.Swingy;
import com.nalexand.swingy.utils.GameLogics;
import com.nalexand.swingy.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class WorldMap {

    private int size = 0;

    private List<List<Cell>> cells = null;

    public List<Hero> mobs = null;

    public void generateWorld(Hero hero) {
        generateMap(hero);
        generateMobs(hero);
        generateObstacles();
    }

    public int getSize() {
        return size;
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public void removeMob(Hero mob) {
        cells.get(mob.posY).get(mob.posX).withMob = false;
        mobs.remove(mob);
    }

    private void generateMap(Hero hero) {
        size = ((hero.level - 1) * 5) + 10 - (hero.level % 2);
        cells = new ArrayList<>(size);

        for (int row = 0; row < size; row++) {
            List<Cell> rowList = new ArrayList<>(size);
            cells.add(rowList);
            for (int column = 0; column < size; column++) {
                rowList.add(new Cell());
            }
        }

        int center = size / 2;
        hero.posX = center;
        hero.posY = center;
        cells.get(center).get(center).withHero = true;
    }

    private void generateMobs(Hero hero) {
        int needGenerateMobs = 2;
        mobs = new ArrayList<>(needGenerateMobs);
        while (needGenerateMobs != 0) {
            int randomX = Utils.randomBetween(0, size - 1);
            int randomY = Utils.randomBetween(0, size - 1);
            Cell cell = cells.get(randomY).get(randomX);

            if (!cell.isFree()) continue;

            Hero newMob = new Hero(Hero.Type.MOB);
            GameLogics.initAsMob(newMob, hero);
            newMob.posX = randomX;
            newMob.posY = randomY;
            mobs.add(newMob);
            cell.withMob = true;
            needGenerateMobs--;
        }
    }

    private void generateObstacles() {
        int needGenerateObstacles = (int) (size * size * Swingy.OBSTACLES_PERCENTAGE);
        while (needGenerateObstacles > 0) {
            int randomX = Utils.randomBetween(0, size - 1);
            int randomY = Utils.randomBetween(0, size - 1);
            Cell cell = cells.get(randomY).get(randomX);

            if (!cell.isFree()) continue;

            cell.isObstacle = true;
            needGenerateObstacles--;
        }
    }
}
