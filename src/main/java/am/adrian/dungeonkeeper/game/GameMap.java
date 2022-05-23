package am.adrian.dungeonkeeper.game;

import am.adrian.dungeonkeeper.common.object.GameObject;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

@Getter
public class GameMap {

    private static final Logger logger = LogManager.getLogger(GameMap.class);

    private final int width;
    private final int height;
    private final GameObject[][] objectMap;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.objectMap = new GameObject[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                objectMap[i][j] = GameObject.EMPTY_SPACE;
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
        objectMap[y][x] = GameObject.EMPTY_SPACE;
    }
}
