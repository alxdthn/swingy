package com.nalexand.swingy.model;

import com.google.gson.annotations.SerializedName;

public class Cell {

    @SerializedName("h")
    public boolean withHero = false;

    @SerializedName("b")
    public boolean isObstacle = false;

    @SerializedName("m")
    public boolean withMob = false;

    public boolean isFree() {
        return !withHero && !isObstacle && !withMob;
    }
}
