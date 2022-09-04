package am.adrian.dungeonkeeper.game.object;

import am.adrian.dungeonkeeper.common.coords.ImmutableCoords;
import am.adrian.dungeonkeeper.common.object.GameObject;
import am.adrian.dungeonkeeper.common.object.wall.Wall;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class Impenetrable implements Wall {

    private final ImmutableCoords coords;

    public Impenetrable(int x, int y) {
        this.coords = new ImmutableCoords(x, y);
    }

    @Override
    public ImmutableCoords getCoords() {
        return coords;
    }

    @Override
    public char getConsoleChar() {
        return '#';
    }

    @Override
    public boolean collides(@NotNull GameObject gameObject) {
        return true;
    }
}
