package am.adrian.dungeonkeeper.game;

import am.adrian.dungeonkeeper.common.object.Creature;
import am.adrian.dungeonkeeper.common.object.GameObject;
import am.adrian.dungeonkeeper.game.tile.UnclaimedPath;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static am.adrian.dungeonkeeper.helper.CollisionChecker.objectsCollide;

@Getter
public class GameMap {

    private static final Logger logger = LogManager.getLogger(GameMap.class);

    private final int width;
    private final int height;
    private final GameObject[][] objectMap;
    private final List<Creature> creatures;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.objectMap = new GameObject[height][width];
        this.creatures = new ArrayList<>();
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                objectMap[i][j] = new UnclaimedPath(j, i);
            }
        }
    }

    public void addObject(GameObject object) {
        if (object.getCoords() == null) {
            logger.debug("Not adding object as its coords are null");
            return;
        }
        logger.debug("Adding object with x: {} and y: {}", object.getCoords().getX(), object.getCoords().getY());
        objectMap[object.getCoords().getY()][object.getCoords().getX()] = object;
    }

    public void addObjects(Collection<? extends GameObject> objectsToAdd) {
        objectsToAdd.forEach(this::addObject);
    }

    public void removeObject(GameObject object) {
        if (object.getCoords() == null) {
            logger.debug("Not removing object as its coords are null");
            return;
        }
        removeObject(object.getCoords().getX(), object.getCoords().getY());
    }

    public void removeObject(int x, int y) {
        objectMap[y][x] = new UnclaimedPath(x, y);
    }

    public void addCreature(Creature creature) {
        if (creature.getCoords() == null) {
            logger.debug("Not adding creature as its coords are null");
            return;
        }

        final var x = creature.getCoords().getX();
        final var y = creature.getCoords().getY();

        final var object = objectMap[y][x];

        if (!(object instanceof UnclaimedPath) && objectsCollide(object, creature)) {
            logger.debug("Not adding creature as its colliding with another object");
            return;
        }

        logger.debug("Adding creature with x: {} and y: {}", x, y);
        creatures.add(creature);
    }

    public void addCreature(Collection<? extends Creature> creaturesToAdd) {
        creaturesToAdd.forEach(this::addCreature);
    }

    public void removeCreature(Creature creature) {
        creatures.remove(creature);
    }
}
