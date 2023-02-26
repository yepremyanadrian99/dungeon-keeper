package am.adrian.dungeonkeeper.common.object;

import am.adrian.dungeonkeeper.common.constant.Direction;

public interface Swims extends Movable {

    void swim(Direction dir);

    int swimDelta();
}
