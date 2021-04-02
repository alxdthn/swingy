package com.nalexand.swingy.model.models;

import com.google.gson.annotations.SerializedName;
import com.nalexand.swingy.utils.TextureProvider;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class Cell implements Serializable {

    @SerializedName("h")
    public boolean withHero = false;

    @SerializedName("b")
    public boolean isObstacle = false;

    @SerializedName("m")
    public boolean withMob = false;

    @NotNull
    @SerializedName("t")
    public String texture = TextureProvider.GRASS;

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

    public void initObstacle() {
        isObstacle = true;
        texture = TextureProvider.getRandomObstacleSprite();
    }

    public boolean isFree() {
        return !withHero && !isObstacle && !withMob && battle == null;
    }
}
