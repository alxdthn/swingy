package com.nalexand.swingy.ui.gui.custom;

import com.nalexand.swingy.model.Cell;
import com.nalexand.swingy.model.ModelFacade;
import com.nalexand.swingy.model.WorldMap;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WorldMapPane extends JPanel {

    private final WorldMap worldMap;

    public WorldMapPane(ModelFacade model) {
        this.worldMap = model.getSelectedHero().worldMap;
    }

    @Override
    public void paint(Graphics graphics) {
        int cellSize = Math.min(getWidth(), getHeight()) / worldMap.getSize();
        int y = 0;
        for (List<Cell> row : worldMap.getCells()) {
            int x = 0;
            for (Cell cell : row) {
                graphics.drawRect(x, y, cellSize, cellSize);
                x += cellSize;
            }
            y += cellSize;
        }
    }
}
