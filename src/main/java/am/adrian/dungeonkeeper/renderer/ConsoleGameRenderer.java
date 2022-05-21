package am.adrian.dungeonkeeper.renderer;

import am.adrian.dungeonkeeper.common.ConsoleGameObject;
import am.adrian.dungeonkeeper.common.Handler;
import am.adrian.dungeonkeeper.game.ConsoleGameMap;
import am.adrian.dungeonkeeper.game.GameStateService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.PrintStream;

@Component
@RequiredArgsConstructor
public class ConsoleGameRenderer implements Handler {

    private static final Logger logger = LogManager.getLogger(ConsoleGameRenderer.class);

    private final GameStateService stateService;
    private final ConsoleGameMap map;
    private final PrintStream output;

    @Value("#{T(Math).min(${display.width}, consoleGameMap.width)}")
    private int displayWidth;
    @Value("#{T(Math).min(${display.height}, consoleGameMap.height)}")
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
        final char[][] buffer = new char[map.getHeight()][map.getWidth()];
        fillBufferWithEmptyChars(buffer);
        fillBufferWithMap(buffer);
        printBuffer(buffer);
        System.out.println();
        Thread.sleep(1000);
    }

    private void fillBufferWithEmptyChars(char[][] buffer) {
        for (int i = yOffset; i < displayHeight + yOffset; ++i) {
            for (int j = xOffset; j < displayWidth + xOffset; ++j) {
                buffer[i][j] = 'O';
            }
        }
    }

    private void fillBufferWithMap(char[][] buffer) {
        map.getObjects().forEach(object -> addObject(buffer, object));
    }

    private void printBuffer(char[][] buffer) {
        for (int i = yOffset; i < displayHeight + yOffset; ++i) {
            for (int j = xOffset; j < displayWidth + xOffset; ++j) {
                output.print(buffer[i][j]);
            }
            output.println();
        }
    }

    private void addObject(char[][] buffer, ConsoleGameObject object) {
        int x = object.getCoords().x();
        int y = object.getCoords().y();
        buffer[y][x] = object.getConsoleChar();
    }
}
