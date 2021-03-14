package nalexand.swingy.ui.console;

import nalexand.swingy.scenario.ScenarioStep;
import nalexand.swingy.scenario.Welcome;
import nalexand.swingy.ui.View;

public class ConsoleView implements View {

    @Override
    public void renderScenarioData(ScenarioStep.Data scenarioData) {
        if (scenarioData instanceof Welcome) {
            showWelcome((Welcome) scenarioData);
        }
    }

    private void showWelcome(Welcome welcome) {
        System.out.println("Console mode activated!");
        System.out.println("1: Create a hero");
        if (welcome.previousCreatedHero != null) {
            System.out.println("2: Select a previously created hero");
        }
    }
}
