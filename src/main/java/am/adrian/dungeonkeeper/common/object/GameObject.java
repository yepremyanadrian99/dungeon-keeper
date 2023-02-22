package am.adrian.dungeonkeeper.common.object;

import am.adrian.dungeonkeeper.common.coords.Coords;

public interface GameObject extends Collidable {

    Coords getCoords();

    char getConsoleChar();

    String getTexture();
}
