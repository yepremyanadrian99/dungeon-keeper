package am.adrian.dungeonkeeper.renderer;

import am.adrian.dungeonkeeper.common.handler.Handler;
import am.adrian.dungeonkeeper.common.object.GameObject;
import am.adrian.dungeonkeeper.game.GameMap;
import am.adrian.dungeonkeeper.game.GameStateService;
import am.adrian.dungeonkeeper.helper.ConsoleUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintStream;

@Component
@RequiredArgsConstructor
public class GameRenderer implements Handler {

    private static final Logger logger = LogManager.getLogger(GameRenderer.class);

    private final GameStateService stateService;
    private final GameMap map;
    private final PrintStream output;

    @Value("#{T(Math).min(${display.width}, gameMap.width)}")
    private int displayWidth;
    @Value("#{T(Math).min(${display.height}, gameMap.height)}")
    private int displayHeight;

    @Getter
    @Setter
    private int xOffset = 0;
    @Getter
    @Setter
    private int yOffset = 0;

    @Override
    public void handle() {
        logger.debug("Handle method called");
        while (!stateService.isFinished()) {
            handleInternal();
        }
        logger.debug("Exiting handle method");
    }

    @SneakyThrows(InterruptedException.class)
    public void handleInternal() {
        try {
            ConsoleUtils.clear();
        } catch (IOException e) {
            logger.error("Exception when clearing the console", e);
        }
        final char[][] buffer = new char[displayHeight][displayWidth];
        try {
            fillBufferWithMap(buffer);
            printBuffer(buffer);
        } catch (Exception e) {
            logger.error("Exception when printing the buffer", e);
        }
        System.out.println();
        Thread.sleep(1000);
    }

    private void fillBufferWithMap(char[][] buffer) {
        for (int i = 0; i < buffer.length; ++i) {
            for (int j = 0; j < buffer[i].length; ++j) {
                addObject(buffer, j, i, map.getObjectMap()[i + yOffset][j + xOffset]);
            }
        }
    }

    private void printBuffer(char[][] buffer) {
        for (int i = yOffset; i < displayHeight + yOffset; ++i) {
            for (int j = xOffset; j < displayWidth + xOffset; ++j) {
                output.print(buffer[i][j]);
            }
            output.println();
        }
    }

    private void addObject(char[][] buffer, int x, int y, GameObject object) {
        buffer[y][x] = object.getConsoleChar();
    }
}
