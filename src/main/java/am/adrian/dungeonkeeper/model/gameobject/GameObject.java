package am.adrian.dungeonkeeper.model.gameobject;

import am.adrian.dungeonkeeper.model.coords.Coords;
import am.adrian.dungeonkeeper.model.gameobject.trait.CanCollide;

public interface GameObject extends CanCollide {

    Coords getCoords();

    String getTexture();
}
