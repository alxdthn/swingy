package nalexand.swingy.model;

import nalexand.swingy.scenario.CreateHero;
import nalexand.swingy.scenario.ScenarioStep;
import nalexand.swingy.scenario.Welcome;
import nalexand.swingy.ui.Command;
import nalexand.swingy.ui.View;

import java.util.ArrayList;
import java.util.List;

public class Model {

    @SuppressWarnings("rawtypes")
    private static final List<ScenarioStep> scenario = new ArrayList<>();

    private int scenarioIterator = 0;

    private View view = null;

    public Model() {
        scenario.add(new Welcome(this));
        scenario.add(new CreateHero(this));
    }

    public void start() {
        scenarioIterator = 0;
        view.renderScenarioData(
            scenario.get(scenarioIterator).provideScenarioData()
        );
    }

    public void resolveCommand(Command command) {
        scenario.get(scenarioIterator).resolve(command);
    }

    public void nextStep() {
        view.renderScenarioData(
                scenario.get(++scenarioIterator).provideScenarioData()
        );
    }

    public void setView(View view) {
        this.view = view;
    }
}
