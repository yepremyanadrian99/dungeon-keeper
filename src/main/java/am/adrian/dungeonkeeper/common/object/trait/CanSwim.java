package am.adrian.dungeonkeeper.common.object.trait;

import am.adrian.dungeonkeeper.common.constant.Direction;

public interface CanSwim extends CanMove {

    void swim(Direction dir);

    int swimDelta();
}
