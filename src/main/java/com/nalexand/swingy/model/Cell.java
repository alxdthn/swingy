package com.nalexand.swingy.model;

import com.google.gson.annotations.SerializedName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

public class Cell {

    @SerializedName("h")
    public boolean withHero = false;

    @SerializedName("b")
    public boolean isObstacle = false;

    @SerializedName("m")
    public boolean withMob = false;

    @Min(0)
    public int x;

    @Min(0)
    public int y;

    @Valid
    public Battle battle = null;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isFree() {
        return !withHero && !isObstacle && !withMob && battle == null;
    }
}
