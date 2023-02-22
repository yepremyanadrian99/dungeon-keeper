package am.adrian.dungeonkeeper.config;

import am.adrian.dungeonkeeper.common.coords.ImmutableCoords;
import am.adrian.dungeonkeeper.common.health.Health;
import am.adrian.dungeonkeeper.controller.GameController;
import am.adrian.dungeonkeeper.game.Game;
import am.adrian.dungeonkeeper.game.GameMap;
import am.adrian.dungeonkeeper.game.GameStateService;
import am.adrian.dungeonkeeper.game.MoveValidator;
import am.adrian.dungeonkeeper.game.character.Goblin;
import am.adrian.dungeonkeeper.game.object.Impenetrable;
import am.adrian.dungeonkeeper.helper.ResourceHelper;
import am.adrian.dungeonkeeper.renderer.GameRenderer2D;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static am.adrian.dungeonkeeper.helper.ObjectGenerator.betweenPoints;

@Configuration
public class GameConfig {

    @Autowired
    private GameMap map;

    @Autowired
    private MoveValidator moveValidator;

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
    public GameMap gameMap(
            @Value("${gameMap.width}") int width,
            @Value("${gameMap.height}") int height
    ) {
        return new GameMap(width, height);
    }

    @Bean
    public GameRenderer2D gameRenderer(
            GameStateService stateService,
            GameMap gameMap,
            ResourceHelper resourceHelper,
            @Value("${window.width}") int width,
            @Value("${window.height}") int height,
            @Value("${window.offsetX}") int offsetX,
            @Value("${window.offsetY}") int offsetY,
            @Value("${window.cell.size}") int cellSize
    ) {
        final var gameRenderer = new GameRenderer2D(stateService, gameMap, resourceHelper, offsetX, offsetY, cellSize);
        gameRenderer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameRenderer.setSize(width, height);
        gameRenderer.setVisible(true);
        gameRenderer.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        gameRenderer.setBackground(Color.BLACK);
        return gameRenderer;
    }

    @Bean
    public Game game(
            GameStateService stateService,
            ExecutorService handlerExecutor,
            GameController gameController,
            GameRenderer2D gameRenderer
    ) {
        final var game = new Game(stateService, handlerExecutor);
        game.registerHandler(gameController);
        game.registerHandler(gameRenderer);
        return game;
    }

    @PostConstruct
    public void initObjects() {
        final int width = map.getWidth();
        final int height = map.getHeight();
        map.addObjects(betweenPoints(new ImmutableCoords(0, 0), new ImmutableCoords(width - 1, 0), Impenetrable::new));
        map.addObjects(betweenPoints(new ImmutableCoords(0, 0), new ImmutableCoords(0, height - 1), Impenetrable::new));
        map.addObjects(betweenPoints(0, height - 1, width - 1, height - 1, Impenetrable::new));
        map.addObjects(betweenPoints(width - 1, 0, width - 1, height - 1, Impenetrable::new));
    }

    @PostConstruct
    public void initCharacters() {
        final var goblin = new Goblin(new Health(100), moveValidator);
        goblin.getCoords().setX(10);
        goblin.getCoords().setY(4);
        map.addCreature(goblin);
    }
}
