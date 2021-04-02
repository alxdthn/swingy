package com.nalexand.swingy.utils;

import com.nalexand.swingy.model.models.Hero;

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

    public static void setIcon(JLabel label, String source) {
        label.setIcon(new ImageIcon(TextureProvider.getImage(source)));
    }

    public static void setIcon(JButton button, String source) {
        button.setIcon(new ImageIcon(TextureProvider.getImage(source)));
    }

    public static void setGifIcon(JLabel dest, String source) {
        dest.setIcon(new ImageIcon(IconResolver.class.getResource(source)));
    }
}
