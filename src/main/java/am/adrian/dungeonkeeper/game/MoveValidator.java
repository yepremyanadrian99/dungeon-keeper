package am.adrian.dungeonkeeper.game;

import am.adrian.dungeonkeeper.common.constant.Direction;
import am.adrian.dungeonkeeper.common.coords.Coords;
import am.adrian.dungeonkeeper.common.object.*;
import am.adrian.dungeonkeeper.common.object.path.Path;
import am.adrian.dungeonkeeper.common.object.path.Water;
import am.adrian.dungeonkeeper.common.object.wall.Wall;
import org.springframework.stereotype.Component;

@Component
public record MoveValidator(GameMap map) {

    public boolean validateWalk(Creature character, Direction dir) {
        if (!(character instanceof Walks)) {
            return false;
        }
        return isMoveWithinMap(character, dir) && objectToMoveTo(character.getCoords(), dir) instanceof Path;
    }

    public boolean validateSwim(Creature character, Direction dir) {
        if (!(character instanceof Swims)) {
            return false;
        }
        return isMoveWithinMap(character, dir) && objectToMoveTo(character.getCoords(), dir) instanceof Water;
    }

    public boolean validateFly(Creature character, Direction dir) {
        if (!(character instanceof Flies)) {
            return false;
        }
        return isMoveWithinMap(character, dir) && !(objectToMoveTo(character.getCoords(), dir) instanceof Wall);
    }

    private boolean isMoveWithinMap(Creature character, Direction dir) {
        return switch (dir) {
            case UP -> character.getCoords().getY() > 0;
            case DOWN -> character.getCoords().getY() < map.getHeight() - 1;
            case LEFT -> character.getCoords().getX() > 0;
            case RIGHT -> character.getCoords().getX() < map.getWidth() - 1;
        };
    }

    private GameObject objectToMoveTo(Coords characterCoords, Direction dir) {
        int x = characterCoords.getX();
        int y = characterCoords.getY();
        return switch (dir) {
            case UP -> map.getObjectMap()[y - 1][x];
            case DOWN -> map.getObjectMap()[y + 1][x];
            case LEFT -> map.getObjectMap()[y][x - 1];
            case RIGHT -> map.getObjectMap()[y][x + 1];
        };
    }
}
