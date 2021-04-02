package com.nalexand.swingy.model.items;

import com.nalexand.swingy.utils.IconResolver;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class Item implements Serializable {

    @NotNull
    public Type type;

    @NotNull
    @NotEmpty
    public String name;

    @Min(0)
    public int attack;

    @Min(0)
    public int defence;

    @Min(0)
    public int hitPoints;

    @NotNull
    @NotEmpty
    public String iconSource;

    public Item(Type type, String name, int attack, int defence, int hitPoints) {
        this.type = type;
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.hitPoints = hitPoints;
        this.iconSource = IconResolver.getItemIcon(this);
    }

    public String getFormattedString() {
        switch (type) {
            case WEAPON:
                return String.format("weapon: %s (attack = %d)", name, attack);
            case ARMOR:
                return String.format("armor: %s (defence = %d)", name, defence);
            default:
                return String.format("helmet: %s (hitPoints = %d)", name, hitPoints);
        }
    }

    public String getDisplayedString() {
        switch (type) {
            case WEAPON:
                return String.format("Attack +%d", attack);
            case ARMOR:
                return String.format("Defence +%d", defence);
            default:
                return String.format("HP +%d", hitPoints);
        }
    }

    public static String safeFormatItem(Item item, Item.Type type) {
        if (item == null) {
            switch (type) {
                case WEAPON:
                    return "weapon: EMPTY";
                case ARMOR:
                    return "armor: EMPTY";
                default:
                    return "helmet: EMPTY";
            }
        } else {
            return item.getFormattedString();
        }
    }

    public enum Type { WEAPON, ARMOR, HELMET }
}
