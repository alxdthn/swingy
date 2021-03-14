package nalexand.swingy.scenario;

import nalexand.swingy.ui.Command;

public interface ScenarioStep<ScenarioData extends ScenarioStep.Data> {

    void resolve(Command command);

    ScenarioData provideScenarioData();

    interface Data {}
}
