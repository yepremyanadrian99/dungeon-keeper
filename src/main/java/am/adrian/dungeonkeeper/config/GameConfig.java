package am.adrian.dungeonkeeper.config;

import am.adrian.dungeonkeeper.controller.ConsoleGameController;
import am.adrian.dungeonkeeper.game.ConsoleGame;
import am.adrian.dungeonkeeper.game.ConsoleGameMap;
import am.adrian.dungeonkeeper.game.GameStateService;
import am.adrian.dungeonkeeper.game.object.Wall;
import am.adrian.dungeonkeeper.helper.ObjectGenerator;
import am.adrian.dungeonkeeper.helper.Point2D;
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
        final ConsoleGameMap map = new ConsoleGameMap(width, height);
        map.addObjects(ObjectGenerator.betweenPoints(new Point2D(0, 0), new Point2D(width - 1, 0), Wall::new));
        map.addObjects(ObjectGenerator.betweenPoints(new Point2D(0, 0), new Point2D(0, height - 1), Wall::new));
        map.addObjects(ObjectGenerator.betweenPoints(0, height - 1, width - 1, height - 1, Wall::new));
        map.addObjects(ObjectGenerator.betweenPoints(width - 1, 0, width - 1, height - 1, Wall::new));
        return map;
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
