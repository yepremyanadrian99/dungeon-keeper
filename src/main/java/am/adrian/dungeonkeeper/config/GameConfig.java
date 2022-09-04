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
import am.adrian.dungeonkeeper.renderer.GameRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
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
    public GameMap gameMap(@Value("${gameMap.width}") int width,
                           @Value("${gameMap.height}") int height) {
        return new GameMap(width, height);
    }

    @Bean
    public Game game(GameStateService stateService,
                     ExecutorService handlerExecutor,
                     GameController gameController,
                     GameRenderer gameRenderer) {
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
        map.addCharacter(goblin);
    }
}
