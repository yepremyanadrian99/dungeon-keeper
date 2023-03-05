package am.adrian.dungeonkeeper.game;

import am.adrian.dungeonkeeper.constant.Direction;
import am.adrian.dungeonkeeper.model.coords.Coords;
import am.adrian.dungeonkeeper.model.gameobject.Creature;
import am.adrian.dungeonkeeper.model.gameobject.GameObject;
import am.adrian.dungeonkeeper.model.gameobject.Path;
import am.adrian.dungeonkeeper.model.gameobject.Wall;
import am.adrian.dungeonkeeper.model.gameobject.tile.Water;
import am.adrian.dungeonkeeper.model.gameobject.trait.CanFly;
import am.adrian.dungeonkeeper.model.gameobject.trait.CanSwim;
import am.adrian.dungeonkeeper.model.gameobject.trait.CanWalk;
import org.springframework.stereotype.Component;

@Component
public record MoveValidator(GameMap map) {

    public boolean validateWalk(Creature character, Direction dir) {
        if (!(character instanceof CanWalk)) {
            return false;
        }
        return isMoveWithinMap(character, dir) && objectToMoveTo(character.getCoords(), dir) instanceof Path;
    }

    public boolean validateSwim(Creature character, Direction dir) {
        if (!(character instanceof CanSwim)) {
            return false;
        }
        return isMoveWithinMap(character, dir) && objectToMoveTo(character.getCoords(), dir) instanceof Water;
    }

    public boolean validateFly(Creature character, Direction dir) {
        if (!(character instanceof CanFly)) {
            return false;
        }
        return isMoveWithinMap(character, dir) && !(objectToMoveTo(character.getCoords(), dir) instanceof Wall);
    }

    private boolean isMoveWithinMap(Creature character, Direction dir) {
        return switch (dir) {
            case UP -> character.getCoords().y() > 0;
            case DOWN -> character.getCoords().y() < map.getHeight() - 1;
            case LEFT -> character.getCoords().x() > 0;
            case RIGHT -> character.getCoords().x() < map.getWidth() - 1;
        };
    }

    private GameObject objectToMoveTo(Coords characterCoords, Direction dir) {
        int x = characterCoords.x();
        int y = characterCoords.y();
        return switch (dir) {
            case UP -> map.getObjectMap()[y - 1][x];
            case DOWN -> map.getObjectMap()[y + 1][x];
            case LEFT -> map.getObjectMap()[y][x - 1];
            case RIGHT -> map.getObjectMap()[y][x + 1];
        };
    }
}
