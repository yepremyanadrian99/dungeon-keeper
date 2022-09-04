package am.adrian.dungeonkeeper.renderer;

import am.adrian.dungeonkeeper.common.constant.RendererCharType;
import am.adrian.dungeonkeeper.common.handler.Handler;
import am.adrian.dungeonkeeper.common.object.Emotional;
import am.adrian.dungeonkeeper.common.object.GameObject;
import am.adrian.dungeonkeeper.common.object.Levelable;
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

    private RendererCharType charToPrint = RendererCharType.OBJECT;

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
            updateCharToPrint();
        } catch (Exception e) {
            logger.error("Exception when printing the buffer", e);
        }
        System.out.println();
        Thread.sleep(500);
    }

    private void fillBufferWithMap(char[][] buffer) {
        for (int i = 0; i < buffer.length; ++i) {
            for (int j = 0; j < buffer[i].length; ++j) {
                addChar(buffer, j, i, map.getObjectMap()[i + yOffset][j + xOffset]);
            }
        }
        for (var character : map.getCharacterList()) {
            addChar(buffer, character);
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

    private void addChar(char[][] buffer, GameObject object) {
        addChar(buffer, object.getCoords().getX(), object.getCoords().getY(), object);
    }

    private void addChar(char[][] buffer, int x, int y, GameObject object) {
        final char character;
        if (charToPrint == RendererCharType.LEVEL && object instanceof Levelable levelable) {
            character = (char) ('0' + levelable.getLevel());
        } else if (charToPrint == RendererCharType.MOOD && object instanceof Emotional emotional) {
            character = emotional.getMood().getSymbol();
        } else {
            character = object.getConsoleChar();
        }
        buffer[y][x] = character;
    }

    private void updateCharToPrint() {
        charToPrint = switch (charToPrint) {
            case OBJECT -> RendererCharType.LEVEL;
            case LEVEL -> RendererCharType.MOOD;
            case MOOD -> RendererCharType.OBJECT;
        };
    }
}
