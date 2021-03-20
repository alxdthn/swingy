package com.nalexand.swingy.model.items;

import com.nalexand.swingy.utils.Utils;

import static com.nalexand.swingy.model.items.Item.Type.*;

public class ItemFactory {

    private static Item[] items = {
            //region Weapons
            new Item(WEAPON, "Divine rapier", 3, 0, 0),
            new Item(WEAPON, "Desolator", 2, 0, 0),
            new Item(WEAPON, "Butterfly", 1, 0, 0),
            //endregion
            //region Armors
            new Item(ARMOR, "Shiva's guard", 0, 2, 0),
            new Item(ARMOR, "Blade mail", 0, 1, 0),
            //endregion
            //region Helmets
            new Item(HELMET, "Dominator", 0, 0, 1)
            //endregion
    };

    private ItemFactory() {
    }

    public static Item randomItem() {
        return items[Utils.randomBetween(0, items.length)];
    }
}
