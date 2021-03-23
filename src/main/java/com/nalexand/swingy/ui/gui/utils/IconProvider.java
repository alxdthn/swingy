package com.nalexand.swingy.ui.gui.utils;

import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.model.items.Item;

import javax.swing.*;

public class IconProvider {

    private IconProvider() {}

    public static ImageIcon getHeroIcon(Hero hero) {
        String resource = null;
        switch (hero.type) {
            case VOID:
                resource = "/void.png";
                break;
            case NEVERMORE:
                resource = "/nevermore.png";
                break;
            case TRAXEX:
                resource = "/traxex.png";
                break;
            case URSA:
                resource = "/ursa.png";
                break;
            case MOB:
                switch (hero.name) {
                    case "Bandit":
                        resource = "/bandit.png";
                        break;
                    case "Necromancer":
                        resource = "/necromancer.png";
                        break;
                    case "Ork":
                        resource = "/ork.png";
                        break;
                    case "Wood Elf":
                        resource = "/wood_lef.png";
                        break;
                }
                break;
        }
        if (resource == null) {
            throw new IllegalStateException(String.format("No icon for %s %s", hero.type, hero.name));
        }
        return new ImageIcon(IconProvider.class.getResource(resource));
    }

    public static ImageIcon getItemIcon(Item item) {
        String resource = null;
        switch (item.name) {
            case "Divine rapier":
                resource = "/rapier.png";
                break;
            case "Desolator":
                resource = "/desolator.png";
                break;
            case "Butterfly":
                resource = "/butterfly.png";
                break;
            case "Shiva's guard":
                resource = "/shiva.png";
                break;
            case "Blade mail":
                resource = "/blade_mail.png";
                break;
            case "Dominator":
                resource = "/dominator.png";
                break;
        }
        if (resource == null) {
            throw new IllegalStateException(String.format("No icon for %s", item.name));
        }
        return new ImageIcon(IconProvider.class.getResource(resource));
    }
}
