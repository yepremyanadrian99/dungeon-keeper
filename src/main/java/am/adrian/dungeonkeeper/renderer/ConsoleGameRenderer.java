package am.adrian.dungeonkeeper.renderer;

import am.adrian.dungeonkeeper.common.Handler;
import am.adrian.dungeonkeeper.game.ConsoleGameMap;
import am.adrian.dungeonkeeper.game.ConsoleGameObject;
import am.adrian.dungeonkeeper.game.GameStateService;
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
public class ConsoleGameRenderer implements Handler {

    private static final Logger logger = LogManager.getLogger(ConsoleGameRenderer.class);

    private final GameStateService stateService;
    private final ConsoleGameMap map;
    private final PrintStream output;

    @Value("${display.width}")
    private int displayWidth;
    @Value("${display.height}")
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
            Runtime.getRuntime().exec("clear");
        } catch (IOException e) {
            e.printStackTrace();
        }
        final char[][] buffer = new char[map.getWidth()][map.getHeight()];
        fillBuffer(buffer);
//        printBuffer(buffer);
        logger.debug("Printing the map...");
        Thread.sleep(1000);
    }

    private void fillBuffer(char[][] buffer) {
        map.getObjects().forEach(object -> addObject(buffer, object));
    }

    private void addObject(char[][] buffer, ConsoleGameObject object) {
        int x = object.getCoords().x();
        int y = object.getCoords().y();
        buffer[x][y] = object.getConsoleChar();
    }

    private void printBuffer(char[][] buffer) {
        for (int i = xOffset; i < xOffset + displayWidth; ++i) {
            for (int j = yOffset; j < yOffset + displayHeight; ++j) {
                output.print(buffer[i][j]);
            }
            output.println();
        }
    }
}
