package am.adrian.dungeonkeeper.common.object.trait;

import am.adrian.dungeonkeeper.common.health.Health;

public interface HasHealth {

    Health getHealth();

    default boolean hasHealth() {
        return getHealth().getLevel() != 0;
    }
}
