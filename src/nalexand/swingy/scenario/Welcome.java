package nalexand.swingy.scenario;

import nalexand.swingy.model.Hero;
import nalexand.swingy.model.Model;
import nalexand.swingy.ui.Command;

public class Welcome extends BaseScenarioStep<Welcome> implements ScenarioStep.Data {

    public Hero previousCreatedHero = null;

    public Welcome(Model model) {
        super(model);
    }

    @Override
    public void resolve(Command command) {
        if (command == Command.OPTION_1) {
            model.nextStep();
        }
    }
}
