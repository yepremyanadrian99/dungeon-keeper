package am.adrian.dungeonkeeper.game.character;

import am.adrian.dungeonkeeper.common.constant.Direction;
import am.adrian.dungeonkeeper.common.coords.MutableCoords;
import am.adrian.dungeonkeeper.common.health.Health;
import am.adrian.dungeonkeeper.common.object.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
@Getter
@Setter
public class Goblin implements Creature, Destroyable, Walks, Swims, Emotional, Levelable {

    private static final String TEXTURE = "goblin.png";

    private static final Logger logger = LogManager.getLogger(Goblin.class);

    private final MutableCoords coords = new MutableCoords();
    private final Health health;
    private final int width;
    private final int height;

    private int level = 1;
    private Mood mood = Mood.HAPPY;

    @Override
    public String getTexture() {
        return TEXTURE;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void destroyedAction() {
        logger.debug("{} has been destroyed/killed.", this.getClass().getName());
    }

    @Override
    public void walk(Direction dir) {
        move(dir, walkDelta());
    }

    @Override
    public int walkDelta() {
        return 5;
    }

    @Override
    public void swim(Direction dir) {
        move(dir, swimDelta());
    }

    @Override
    public int swimDelta() {
        return 1;
    }

    @Override
    public void levelUp() {
        ++level;
    }

    public void move(Direction dir, int delta) {
        switch (dir) {
            case UP -> coords.incY(-delta);
            case DOWN -> coords.incY(delta);
            case LEFT -> coords.incX(-delta);
            case RIGHT -> coords.incX(delta);
            default -> throw new RuntimeException("Unknown direction");
        }
    }

    @Override
    public boolean collides(@NotNull GameObject gameObject) {
        return false;
    }
}
