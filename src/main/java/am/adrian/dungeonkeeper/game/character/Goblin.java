package am.adrian.dungeonkeeper.game.character;

import am.adrian.dungeonkeeper.common.constant.Direction;
import am.adrian.dungeonkeeper.common.coords.MutableCoords;
import am.adrian.dungeonkeeper.common.health.Health;
import am.adrian.dungeonkeeper.common.object.*;
import am.adrian.dungeonkeeper.game.MoveValidator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
@Getter
@Setter
public class Goblin implements GameCharacter, Destroyable, Walks, Swims, Emotional, Levelable {

    private static final char CONSOLE_CHAR = 'G';

    private static final Logger logger = LogManager.getLogger(Goblin.class);

    private final MutableCoords coords = new MutableCoords();
    private final Health health;
    private final MoveValidator moveValidator;

    private int level = 0;
    private Mood mood = Mood.HAPPY;

    @Override
    public char getConsoleChar() {
        return CONSOLE_CHAR;
    }

    @Override
    public void destroyedAction() {
        logger.debug("{} has been destroyed/killed.", this.getClass().getName());
    }

    @Override
    public void walk(Direction dir) {
        move(dir);
    }

    @Override
    public void swim(Direction dir) {
        move(dir);
    }

    @Override
    public void levelUp() {
        ++level;
    }

    private void move(Direction dir) {
        switch (dir) {
            case UP -> coords.decY();
            case DOWN -> coords.incY();
            case LEFT -> coords.decX();
            case RIGHT -> coords.incX();
            default -> throw new RuntimeException("Unknown direction");
        }
    }

    @Override
    public boolean collides(@NotNull GameObject gameObject) {
        return false;
    }
}
