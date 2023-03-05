package am.adrian.dungeonkeeper.model.gameobject.trait;

import am.adrian.dungeonkeeper.model.health.Health;

public interface HasHealth {

    Health getHealth();

    default boolean hasHealth() {
        return getHealth().getLevel() != 0;
    }
}
