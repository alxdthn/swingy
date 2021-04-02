package com.nalexand.swingy.model.models.items;

import com.nalexand.swingy.utils.Utils;

import static com.nalexand.swingy.model.models.items.Item.Type.*;

public class ItemFactory {

    private static Item[] items = {
            //region Weapons
            new Item(WEAPON, "Divine rapier", 3, 0, 0, "/rapier.png"),
            new Item(WEAPON, "Desolator", 2, 0, 0, "/desolator.png"),
            new Item(WEAPON, "Butterfly", 1, 0, 0, "/butterfly.png"),
            //endregion
            //region Armors
            new Item(ARMOR, "Assault Cuirass", 0, 3, 0, "/cuirass.png"),
            new Item(ARMOR, "Shiva's guard", 0, 2, 0, "/shiva.png"),
            new Item(ARMOR, "Blade mail", 0, 1, 0, "/blade_mail.png"),
            //endregion
            //region Helmets
            new Item(HELMET, "Dominator", 0, 0, 3, "/dominator.png"),
            new Item(HELMET, "Helm of Iron Will", 0, 0, 2, "/iron_helm.png"),
            new Item(HELMET, "Hood of Defiance", 0, 0, 1, "/hood.png"),
            //endregion
    };

    private ItemFactory() {
    }

    public static Item randomItem() {
        return items[Utils.randomBetween(0, items.length)];
    }
}
