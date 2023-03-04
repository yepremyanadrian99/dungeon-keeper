package am.adrian.dungeonkeeper.config;

import am.adrian.dungeonkeeper.game.Game;
import am.adrian.dungeonkeeper.game.GameMap;
import am.adrian.dungeonkeeper.game.GameStateService;
import am.adrian.dungeonkeeper.game.controller.GameKeyController;
import am.adrian.dungeonkeeper.game.controller.GameRenderController;
import am.adrian.dungeonkeeper.helper.ResourceHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.swing.*;
import java.awt.*;

@Configuration
@EnableScheduling
public class GameConfig {

    @Bean
    public GameMap gameMap(
            @Value("${gameMap.width}") int width,
            @Value("${gameMap.height}") int height
    ) {
        return new GameMap(width, height);
    }

    @Bean
    public GameRenderController gameRenderController(
            GameMap gameMap,
            ResourceHelper resourceHelper,
            @Value("${window.offsetX}") int offsetX,
            @Value("${window.offsetY}") int offsetY,
            @Value("${window.cell.size}") int cellSize,
            @Value("${window.map.outlines}") boolean outlines
    ) {
        return new GameRenderController(
                gameMap,
                resourceHelper,
                offsetX,
                offsetY,
                cellSize,
                outlines
        );
    }

    @Bean
    public GameKeyController gameKeyController(
            GameMap gameMap,
            @Value("${window.cell.size}") int cellSize
    ) {
        return new GameKeyController(gameMap, cellSize);
    }


    @Bean
    public Game game(
            GameStateService stateService,
            GameRenderController gameRenderer,
            GameKeyController keyController,
            @Value("${window.width}") int width,
            @Value("${window.height}") int height
    ) {
        final var game = new Game(stateService, gameRenderer, keyController);
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setSize(width, height);
        game.setVisible(true);
        game.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        game.setBackground(Color.BLACK);
        return game;
    }
}
