package am.adrian.dungeonkeeper.model.gameobject.trait;

import am.adrian.dungeonkeeper.constant.Direction;

public interface CanWalk extends CanMove {

    void walk(Direction dir);

    int walkDelta();
}
