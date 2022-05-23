package am.adrian.dungeonkeeper.common.object;

import am.adrian.dungeonkeeper.common.health.Health;

public interface Destroyable {

    Health getHealth();

    void destroyedAction();

    default boolean isDestroyed() {
        return getHealth().getLevel() == 0;
    }
}
