package am.adrian.dungeonkeeper.game.object;

import am.adrian.dungeonkeeper.game.ConsoleGameObject;
import am.adrian.dungeonkeeper.helper.Point2D;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Wall implements ConsoleGameObject {

    private final Point2D coords;

    public Wall(int x, int y) {
        this.coords = new Point2D(x, y);
    }

    @Override
    public Point2D getCoords() {
        return coords;
    }

    @Override
    public char getConsoleChar() {
        return '#';
    }
}
