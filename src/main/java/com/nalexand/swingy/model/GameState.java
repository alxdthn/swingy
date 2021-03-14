package com.nalexand.swingy.model;

import com.nalexand.swingy.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameState {

    private Hero.Type selectedHero = null;

    private Map<Hero.Type, Hero> heroes = new HashMap<>();

    public GameState() {
        heroes.put(Hero.Type.VOID, new Hero(Hero.Type.VOID));
        heroes.put(Hero.Type.NEVERMORE, new Hero(Hero.Type.NEVERMORE));
        heroes.put(Hero.Type.TRAXES, new Hero(Hero.Type.TRAXES));
        heroes.put(Hero.Type.URSA, new Hero(Hero.Type.URSA));
    }

    public Hero getSelectedHero() {
        if (selectedHero == null) return null;
        return heroes.get(selectedHero);
    }

    public void setSelectedHero(Hero.Type selectedHeroType) {
        selectedHero = selectedHeroType;
        heroes.get(selectedHeroType).created = true;
        Utils.saveGameState(this);
    }

    public List<Hero> getCreatedHeroes() {
        return heroes
                .values()
                .stream()
                .filter(hero -> hero.created)
                .collect(Collectors.toList());
    }

    public List<Hero> getAvailableForCreateHeroes() {
        return new ArrayList<>(heroes.values());
    }
}
