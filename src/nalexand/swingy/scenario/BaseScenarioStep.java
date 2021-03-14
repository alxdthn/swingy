package nalexand.swingy.scenario;

import nalexand.swingy.model.Model;

public abstract class BaseScenarioStep<Child extends ScenarioStep.Data> implements ScenarioStep<Child> {

    protected Model model;

    protected BaseScenarioStep(Model model) {
        this.model = model;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Child provideScenarioData() {
        return (Child) this;
    }
}
