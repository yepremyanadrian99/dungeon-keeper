package am.adrian.dungeonkeeper.game.object;

import am.adrian.dungeonkeeper.common.coords.Coords;
import am.adrian.dungeonkeeper.common.object.path.Path;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnclaimedPath implements Path {

    @Override
    public Coords getCoords() {
        throw new RuntimeException(this.getClass().getName() + " does not have coordinates");
    }

    @Override
    public char getConsoleChar() {
        return ' ';
    }
}
