package com.nalexand.swingy.model.models.items;

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

    @Min(1)
    public int level = 1;

    @NotNull
    @NotEmpty
    public String iconSource;

    public Item(Type type, String name, int attack, int defence, int hitPoints, String iconSource) {
        this.type = type;
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.hitPoints = hitPoints;
        this.iconSource = iconSource;
    }

    public void upLevel() {
        level++;
        switch (type) {
            case WEAPON:
                attack++;
                break;
            case ARMOR:
                defence++;
                break;
            case HELMET:
                hitPoints++;
                break;
        }
    }

    public String getFormattedString() {
        switch (type) {
            case WEAPON:
                return String.format("weapon: %s LVL %d attack = %d", name, level, attack);
            case ARMOR:
                return String.format("armor: %s LVL %d defence = %d", name, level, defence);
            default:
                return String.format("helmet: %s LVL %d hitPoints = %d", name, level, hitPoints);
        }
    }

    public String getDisplayedString() {
        switch (type) {
            case WEAPON:
                return String.format("<html>%s LVL %d<br/>Attack +%d<html/>", name, level, attack);
            case ARMOR:
                return String.format("<html>%s LVL %d<br/>Defence +%d<html/>", name, level, defence);
            default:
                return String.format("<html>%s LVL %d<br/>HP +%d<html/>", name, level, hitPoints);
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
