package com.nalexand.swingy.controller;

import com.nalexand.swingy.model.models.items.Item;

public interface BattleWinController {

    void accept();

    void takeLootItem(Item item);
}
