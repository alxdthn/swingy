package nalexand.swingy.scenario;

import nalexand.swingy.model.Model;
import nalexand.swingy.ui.Command;

public class CreateHero extends BaseScenarioStep<CreateHero> implements ScenarioStep.Data {

    public CreateHero(Model model) {
        super(model);
    }

    @Override
    public void resolve(Command command) {
        switch (command) {
            case OPTION_1:
        }
    }
}
