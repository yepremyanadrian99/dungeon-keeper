package am.adrian.dungeonkeeper.game.character;

import am.adrian.dungeonkeeper.common.constant.Direction;
import am.adrian.dungeonkeeper.common.coords.MutableCoords;
import am.adrian.dungeonkeeper.common.health.Health;
import am.adrian.dungeonkeeper.common.object.Destroyable;
import am.adrian.dungeonkeeper.common.object.GameCharacter;
import am.adrian.dungeonkeeper.common.object.Swims;
import am.adrian.dungeonkeeper.common.object.Walks;
import am.adrian.dungeonkeeper.game.MoveValidator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RequiredArgsConstructor
@Getter
public class Goblin implements GameCharacter, Destroyable, Walks, Swims {

    private static final char CONSOLE_CHAR = 'G';

    private static final Logger logger = LogManager.getLogger(Goblin.class);

    private final MutableCoords coords = new MutableCoords();
    private final Health health;
    private final MoveValidator moveValidator;

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

    private void move(Direction dir) {
        switch (dir) {
            case UP -> coords.decY();
            case DOWN -> coords.incY();
            case LEFT -> coords.decX();
            case RIGHT -> coords.incX();
            default -> throw new RuntimeException("Unknown direction");
        }
    }
}
