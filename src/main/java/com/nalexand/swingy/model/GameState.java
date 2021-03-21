package com.nalexand.swingy.model;

import java.util.HashMap;
import java.util.Map;

public class GameState {

    public Hero.Type selectedHeroType = null;

    public Map<Hero.Type, Hero> heroes = new HashMap<>();

    public GameState() {
        heroes.put(Hero.Type.VOID, new Hero(Hero.Type.VOID));
        heroes.put(Hero.Type.NEVERMORE, new Hero(Hero.Type.NEVERMORE));
        heroes.put(Hero.Type.TRAXEX, new Hero(Hero.Type.TRAXEX));
        heroes.put(Hero.Type.URSA, new Hero(Hero.Type.URSA));
    }
}
