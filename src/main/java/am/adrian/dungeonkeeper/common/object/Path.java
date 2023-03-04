package am.adrian.dungeonkeeper.common.object;

import org.jetbrains.annotations.NotNull;

public interface Path extends GameObject {

    @Override
    default boolean collides(@NotNull GameObject gameObject) {
        return false;
    }
}
