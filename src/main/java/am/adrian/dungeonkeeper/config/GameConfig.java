package am.adrian.dungeonkeeper.config;

import am.adrian.dungeonkeeper.controller.ConsoleGameController;
import am.adrian.dungeonkeeper.game.ConsoleGame;
import am.adrian.dungeonkeeper.game.ConsoleGameMap;
import am.adrian.dungeonkeeper.game.GameStateService;
import am.adrian.dungeonkeeper.renderer.ConsoleGameRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class GameConfig {

    // Should only be used for executing handlers.
    // Otherwise, the game might not exit after it's finished.
    @Bean
    public ExecutorService handlerExecutor() {
        return Executors.newCachedThreadPool();
    }

    @Bean
    public Scanner playerInputScanner(InputStream input) {
        final Scanner inputScanner = new Scanner(input);
        // To always get single char input
        inputScanner.useDelimiter("");
        return inputScanner;
    }

    @Bean
    public InputStream input() {
        return System.in;
    }

    @Bean
    public PrintStream output() {
        return System.out;
    }

    @Bean
    public ConsoleGameMap consoleGameMap(@Value("${gameMap.width}") int width,
                                         @Value("${gameMap.height}") int height) {
        return new ConsoleGameMap(width, height);
    }

    @Bean
    public ConsoleGame consoleGame(GameStateService stateService,
                                   ExecutorService handlerExecutor,
                                   ConsoleGameController gameController,
                                   ConsoleGameRenderer gameRenderer) {
        final ConsoleGame consoleGame = new ConsoleGame(stateService, handlerExecutor);
        consoleGame.registerHandler(gameController);
        consoleGame.registerHandler(gameRenderer);
        return consoleGame;
    }
}
