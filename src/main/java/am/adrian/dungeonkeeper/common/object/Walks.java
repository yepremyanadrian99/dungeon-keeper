package am.adrian.dungeonkeeper.common.object;

import am.adrian.dungeonkeeper.common.constant.Direction;

public interface Walks extends Movable {

    void walk(Direction dir);

    int walkDelta();
}
