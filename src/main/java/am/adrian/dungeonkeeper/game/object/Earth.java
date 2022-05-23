package am.adrian.dungeonkeeper.game.object;

import am.adrian.dungeonkeeper.common.coords.ImmutableCoords;
import am.adrian.dungeonkeeper.common.object.wall.Wall;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Earth implements Wall {

    private final ImmutableCoords coords;

    public Earth(int x, int y) {
        this.coords = new ImmutableCoords(x, y);
    }

    @Override
    public ImmutableCoords getCoords() {
        return coords;
    }

    @Override
    public char getConsoleChar() {
        return 'O';
    }
}
