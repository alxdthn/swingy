package com.nalexand.swingy.model;

import com.google.gson.annotations.SerializedName;
import com.nalexand.swingy.utils.Colors;

import java.util.ArrayList;
import java.util.List;

public class WorldMap {

    private int size = 0;

    private List<List<Cell>> cells = null;

    public void generateMap(int size) {

        this.size = size;
        cells = new ArrayList<>();

        for (int row = 0; row < size; row++) {
            List<Cell> rowList = new ArrayList<>();
            cells.add(rowList);
            for (int column = 0; column < size; column++) {
                rowList.add(new Cell());
            }
        }

        int center = size / 2;
        cells.get(center).get(center).withHero = true;
    }

    public int getSize() {
        return size;
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public class Cell {

        @SerializedName("h")
        boolean withHero = false;

        @Override
        public String toString() {
            if (withHero) {
                return Colors.YELLOW + "[H]" + Colors.END;
            } else {
                return "[0]";
            }
        }
    }
}
