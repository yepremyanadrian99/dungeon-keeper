package am.adrian.dungeonkeeper.common.object.trait;

import am.adrian.dungeonkeeper.common.constant.Direction;

public interface CanWalk extends CanMove {

    void walk(Direction dir);

    int walkDelta();
}
