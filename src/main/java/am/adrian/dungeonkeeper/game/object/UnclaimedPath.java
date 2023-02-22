package am.adrian.dungeonkeeper.game.object;

import am.adrian.dungeonkeeper.common.coords.Coords;
import am.adrian.dungeonkeeper.common.coords.ImmutableCoords;
import am.adrian.dungeonkeeper.common.object.GameObject;
import am.adrian.dungeonkeeper.common.object.path.Path;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class UnclaimedPath implements Path {

    private final ImmutableCoords coords;

    public UnclaimedPath(int x, int y) {
        this.coords = new ImmutableCoords(x, y);
    }

    @Override
    public Coords getCoords() {
        return coords;
    }

    @Override
    public char getConsoleChar() {
        return ' ';
    }

    @Override
    public String getTexture() {
        return "dirt.jpg";
    }

    @Override
    public boolean collides(@NotNull GameObject gameObject) {
        return false;
    }
}
