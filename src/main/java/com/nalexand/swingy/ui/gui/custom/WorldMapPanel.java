package com.nalexand.swingy.ui.gui.custom;

import com.nalexand.swingy.model.Cell;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.WorldMap;
import com.nalexand.swingy.ui.gui.utils.TextureProvider;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class WorldMapPanel extends JPanel {

    private final WorldMap worldMap;

    public WorldMapPanel(ModelFacade model) {
        super();
        this.worldMap = model.getSelectedHero().worldMap;
    }

    @Override
    public void paint(Graphics graphics) {
        try {
            drawWordMap(graphics);
        } catch (IOException e) {
            System.exit(1);
        }
    }

    private void drawWordMap(Graphics graphics) throws IOException {
        int cellSize = Math.min(getWidth(), getHeight()) / worldMap.getSize();
        int y = 0;
        for (List<Cell> row : worldMap.getCells()) {
            int x = 0;
            for (Cell cell : row) {
                Image texture;
                if (cell.isFree()) {
                    texture = TextureProvider.getGrassTexture();
                } else if (cell.isObstacle) {
                    texture = TextureProvider.getWaterTexture();
                } else if (cell.withMob) {
                    texture = TextureProvider.getGrassWithMobTexture();
                } else if (cell.withHero) {
                    texture = TextureProvider.getGrassWithHeroTexture();
                } else if (cell.battle != null) {
                    texture = TextureProvider.getGrassWithLootTexture();
                } else {
                    throw new IllegalStateException();
                }
                graphics.drawImage(texture, x, y, cellSize, cellSize, null);
                x += cellSize;
            }
            y += cellSize;
        }
    }
}
