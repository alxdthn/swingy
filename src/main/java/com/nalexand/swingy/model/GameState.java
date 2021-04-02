package com.nalexand.swingy.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GameState implements Serializable {

    public Hero.Type selectedHeroType = null;

    @Valid
    @NotNull
    @Size(min = 4, max = 4)
    public Map<Hero.Type, Hero> heroes = new HashMap<>();

    public GameState() {
        heroes.put(Hero.Type.VOID, new Hero(Hero.Type.VOID));
        heroes.put(Hero.Type.NEVERMORE, new Hero(Hero.Type.NEVERMORE));
        heroes.put(Hero.Type.TRAXEX, new Hero(Hero.Type.TRAXEX));
        heroes.put(Hero.Type.URSA, new Hero(Hero.Type.URSA));
    }
}
