package am.adrian.dungeonkeeper.config;

import am.adrian.dungeonkeeper.game.ConsoleGame;
import am.adrian.dungeonkeeper.game.ConsoleGameMap;
import am.adrian.dungeonkeeper.game.GameStateService;
import am.adrian.dungeonkeeper.renderer.GameMapRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.PrintStream;

@Configuration
public class GameConfig {

    @Bean
    public PrintStream printStream() {
        return System.out;
    }

    @Bean
    public ConsoleGameMap consoleGameMap(@Value("${gameMap.width}") int width,
                                         @Value("${gameMap.height}") int height) {
        return new ConsoleGameMap(width, height);
    }

    @Bean
    public ConsoleGame consoleGame(GameMapRenderer renderer, GameStateService stateService) {
        return new ConsoleGame(renderer, stateService);
    }
}
