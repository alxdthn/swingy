package com.nalexand.swingy.controller;

import com.nalexand.swingy.model.models.Hero;

public interface WelcomeController {

    void showCreateHero();

    void selectHeroAndShowGameProcess(Hero hero);
}