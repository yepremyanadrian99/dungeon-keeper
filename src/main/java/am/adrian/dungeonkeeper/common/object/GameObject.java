package am.adrian.dungeonkeeper.common.object;

import am.adrian.dungeonkeeper.common.coords.Coords;
import am.adrian.dungeonkeeper.common.object.trait.CanCollide;

public interface GameObject extends CanCollide {

    Coords getCoords();

    String getTexture();
}
