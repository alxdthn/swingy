package com.nalexand.swingy.model;

import com.nalexand.swingy.controller.Command;
import com.nalexand.swingy.scenario.CreateHero;
import com.nalexand.swingy.scenario.Step;
import com.nalexand.swingy.scenario.Welcome;
import com.nalexand.swingy.view.View;
import com.nalexand.swingy.view.ViewType;
import com.nalexand.swingy.view.console.ConsoleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {

    private static final Map<ViewType, View> view = new HashMap<>();

    private static final List<Step> scenario = new ArrayList<>();

    private int scenarioIterator = 0;

    ViewType currentViewType;

    public Model(ViewType viewType) {
        currentViewType = viewType;

        scenario.add(new Welcome(this));
        scenario.add(new CreateHero(this));

        view.put(ViewType.CONSOLE, new ConsoleView());

        scenario.get(scenarioIterator).render(getView());
    }

    public void resolveCommand(Command command) {
        scenario.get(scenarioIterator).resolve(command);
    }

    public void nextStep() {
        scenarioIterator++;
    }

    private View getView() {
        return view.get(currentViewType);
    }
}
