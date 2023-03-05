package am.adrian.dungeonkeeper.model.gameobject.trait;

import am.adrian.dungeonkeeper.constant.Direction;

public interface CanSwim extends CanMove {

    void swim(Direction dir);

    int swimDelta();
}
