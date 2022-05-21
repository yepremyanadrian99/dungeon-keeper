package am.adrian.dungeonkeeper.renderer;

import am.adrian.dungeonkeeper.game.ConsoleGameMap;
import am.adrian.dungeonkeeper.game.ConsoleGameObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintStream;

@Component
@RequiredArgsConstructor
public class ConsoleGameMapRenderer implements GameMapRenderer {

    private final ConsoleGameMap map;
    private final PrintStream outStream;
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
    public void render() {
        try {
            Runtime.getRuntime().exec("clear");
        } catch (IOException e) {
            e.printStackTrace();
        }
        final char[][] buffer = new char[map.getWidth()][map.getHeight()];
        fillBuffer(buffer);
        printBuffer(buffer);
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
                outStream.print(buffer[i][j]);
            }
            outStream.println();
        }
    }
}
