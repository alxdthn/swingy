package com.nalexand.swingy.ui.gui.utils;

import com.nalexand.swingy.model.Hero;
import com.nalexand.swingy.model.items.Item;

import javax.swing.*;

public class IconResolver {

    private IconResolver() {
    }

    public static String getHeroIcon(Hero hero) {
        switch (hero.type) {
            case VOID:
                return TextureProvider.VOID;
            case NEVERMORE:
                return TextureProvider.NEVERMORE;
            case TRAXEX:
                return TextureProvider.TRAXEX;
            case URSA:
                return TextureProvider.URSA;
            case MOB:
                switch (hero.name) {
                    case "Bandit":
                        return TextureProvider.BANDIT;
                    case "Necromancer":
                        return TextureProvider.NECROMANCER;
                    case "Ork":
                        return TextureProvider.ORK;
                    case "Wood Elf":
                        return TextureProvider.WOOD_ELF;
                }
                break;
        }
        throw new IllegalStateException(String.format("No icon for %s %s", hero.type, hero.name));
    }

    public static String getItemIcon(Item item) {
        switch (item.name) {
            case "Divine rapier":
                return TextureProvider.RAPIER;
            case "Desolator":
                return TextureProvider.DESOLATOR;
            case "Butterfly":
                return TextureProvider.BUTTERFLY;
            case "Shiva's guard":
                return TextureProvider.SHIVA;
            case "Blade mail":
                return TextureProvider.BLADE_MAIL;
            case "Dominator":
                return TextureProvider.DOMINATOR;
        }
        throw new IllegalStateException(String.format("No icon for %s", item.name));
    }


    public static void setIcon(JLabel label, String source) {
        label.setIcon(new ImageIcon(TextureProvider.getImage(source)));
    }

    public static void setIcon(JButton button, String source) {
        button.setIcon(new ImageIcon(TextureProvider.getImage(source)));
    }
}
