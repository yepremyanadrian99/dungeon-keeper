package am.adrian.dungeonkeeper.controller;

import am.adrian.dungeonkeeper.common.Handler;
import am.adrian.dungeonkeeper.constant.GameState;
import am.adrian.dungeonkeeper.game.GameStateService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ConsoleGameController implements Handler {

    private final Logger logger = LogManager.getLogger(ConsoleGameController.class);

    private final GameStateService stateService;
    private final Scanner playerInput;

    @Override
    public void handle() {
        logger.debug("Handle method called");
        while (!stateService.isFinished()) {
            handleInternal();
        }
        logger.debug("Exiting handle method");
    }

    public void handleInternal() {
        final char input = playerInput.next().charAt(0);
        handleUserInput(input);
    }

    private void handleUserInput(char input) {
        logger.debug("Got user input: " + input);
        if (input == 'f' || input == 'F') {
            stateService.setState(GameState.FINISHED);
            logger.debug("User inputted F, exiting...");
        }
    }
}
