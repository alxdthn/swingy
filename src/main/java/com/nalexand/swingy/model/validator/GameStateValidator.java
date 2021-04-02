package com.nalexand.swingy.model.validator;

import com.nalexand.swingy.model.GameState;
import com.nalexand.swingy.model.Hero;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Locale;
import java.util.Set;

public class GameStateValidator {

    private final Validator validator;

    {
        Locale.setDefault(Locale.ENGLISH);
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public GameState validate(GameState gameState) {
        Set<ConstraintViolation<GameState>> violations = validator.validate(gameState);

        if (!violations.isEmpty()) {
            System.err.println("Save loading error:");
            for (ConstraintViolation<GameState> violation : violations) {
                System.err.printf("%s %s\n",
                        violation.getPropertyPath(),
                        violation.getMessage()
                );
            }
            return new GameState();
        }
        int selectedCount = 0;
        for (Hero hero : gameState.heroes.values()) {
            if (hero.selected) {
                selectedCount++;
            }
            if (selectedCount > 1) {
                System.err.println("Save loading error:");
                System.err.println("Only one selected hero required");
                return new GameState();
            }
        }

        return gameState;
    }
}
