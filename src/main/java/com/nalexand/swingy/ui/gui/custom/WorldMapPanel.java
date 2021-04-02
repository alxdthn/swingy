package com.nalexand.swingy.ui.gui.custom;

import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.models.Cell;
import com.nalexand.swingy.model.models.WorldMap;
import com.nalexand.swingy.utils.TextureProvider;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class WorldMapPanel extends JPanel {

    private final WorldMap worldMap;

    private final ModelFacade model;

    public WorldMapPanel(ModelFacade model) {
        super();
        this.worldMap = model.getSelectedHero().worldMap;
        this.model = model;
    }

    @Override
    public void paint(Graphics graphics) {
        try {
            drawWordMap(graphics);
        } catch (IOException e) {
            //TODO ERROR
            System.exit(1);
        }
    }

    private void drawWordMap(Graphics graphics) throws IOException {
        int cellSize = Math.min(getWidth(), getHeight()) / worldMap.getSize();
        int drawY = 0;
        int startX = (getWidth() - cellSize * worldMap.getSize()) / 2;

        int y = 0;
        while (y < worldMap.getSize()) {

            int x = 0;
            int drawX = startX;
            while (x < worldMap.getSize()) {
                Cell cell = worldMap.getCell(x, y);
                Image texture;
                if (cell.withMob) {
                    texture = TextureProvider.getImageWith(
                            cell.texture,
                            model.getMobWithPosition(cell.x, cell.y).iconSource
                    );
                } else if (cell.withHero) {
                    texture = TextureProvider.getImageWith(
                            cell.texture,
                            model.getSelectedHero().iconSource
                    );
                } else if (cell.battle != null) {
                    texture = TextureProvider.getImageWith(
                            cell.texture,
                            cell.battle.mob.getItem().iconSource
                    );
                } else if (cell.isObstacle) {
                    texture = TextureProvider.getImageWith(
                            TextureProvider.GRASS,
                            cell.texture
                    );
                } else {
                    texture = TextureProvider.getImage(cell.texture);
                }
                graphics.drawImage(texture, drawX, drawY, cellSize, cellSize, null);
                x++;
                drawX += cellSize;
            }
            y++;
            drawY += cellSize;
        }
    }
}
