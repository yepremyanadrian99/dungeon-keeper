package am.adrian.dungeonkeeper.config;

import am.adrian.dungeonkeeper.game.GameMap;
import am.adrian.dungeonkeeper.game.GameStateService;
import am.adrian.dungeonkeeper.game.MoveValidator;
import am.adrian.dungeonkeeper.helper.ResourceHelper;
import am.adrian.dungeonkeeper.renderer.GameRenderer2D;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;
import java.awt.*;
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
            MoveValidator moveValidator,
            ResourceHelper resourceHelper,
            @Value("${window.width}") int width,
            @Value("${window.height}") int height,
            @Value("${window.offsetX}") int offsetX,
            @Value("${window.offsetY}") int offsetY,
            @Value("${window.cell.size}") int cellSize,
            @Value("${window.map.outlines}") boolean outlines
    ) {
        final var gameRenderer = new GameRenderer2D(
                stateService, gameMap, moveValidator, resourceHelper,
                offsetX, offsetY, cellSize, outlines
        );
        gameRenderer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameRenderer.setSize(width, height);
        gameRenderer.setVisible(true);
        gameRenderer.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        gameRenderer.setBackground(Color.BLACK);
        return gameRenderer;
    }
}
