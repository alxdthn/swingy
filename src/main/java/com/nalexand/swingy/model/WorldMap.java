package com.nalexand.swingy.model;

import com.nalexand.swingy.utils.GameLogics;
import com.nalexand.swingy.utils.Utils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class WorldMap {

    @Min(0)
    private int size = 0;

    @Valid
    @NotNull
    private List<Cell> cells = null;

    @Valid
    @NotNull
    public List<Hero> mobs = null;

    public void generateWorld(Hero hero) {
        generateMap(hero);
        generateMobs(hero);
        generateObstacles();
    }

    public int getSize() {
        return size;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public Cell getCell(int x, int y) {
        return cells.get(y * size + x);
    }

    public void removeMob(Hero mob) {
        getCell(mob.posX, mob.posY).withMob = false;
        mobs.remove(mob);
    }

    private void generateMap(Hero hero) {
        size = GameLogics.calculateMapSize(hero.level);
        cells = new ArrayList<>(size);

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                cells.add(new Cell(x, y));
            }
        }

        int center = size / 2;
        hero.posX = center;
        hero.posY = center;
        getCell(center, center).withHero = true;
    }

    private void generateMobs(Hero hero) {
        int needGenerateMobs = 2;
        mobs = new ArrayList<>(needGenerateMobs);
        while (needGenerateMobs != 0) {
            int randomX = Utils.randomBetween(0, size - 1);
            int randomY = Utils.randomBetween(0, size - 1);
            Cell cell = getCell(randomX, randomY);

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
        int needGenerateObstacles = GameLogics.calculateNumberOfObstacles(size);
        while (needGenerateObstacles > 0) {
            int randomX = Utils.randomBetween(0, size - 1);
            int randomY = Utils.randomBetween(0, size - 1);
            Cell cell = getCell(randomX, randomY);

            if (!cell.isFree()) continue;

            cell.initObstacle();
            needGenerateObstacles--;
        }
    }
}
