package com.nalexand.swingy.controller;

import com.nalexand.swingy.model.items.Item;

public interface BattleWinController {

    void accept();

    void takeLootItem(Item item);
}
