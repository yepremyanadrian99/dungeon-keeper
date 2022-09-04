package am.adrian.dungeonkeeper.common.object;

import am.adrian.dungeonkeeper.common.coords.Coords;
import am.adrian.dungeonkeeper.game.object.UnclaimedPath;

public interface GameObject extends Collidable {

    GameObject EMPTY_SPACE = new UnclaimedPath();

    Coords getCoords();

    char getConsoleChar();
}
