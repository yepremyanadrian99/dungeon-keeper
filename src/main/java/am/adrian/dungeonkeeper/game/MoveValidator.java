package am.adrian.dungeonkeeper.game;

import am.adrian.dungeonkeeper.common.constant.Direction;
import am.adrian.dungeonkeeper.common.coords.Coords;
import am.adrian.dungeonkeeper.common.object.Creature;
import am.adrian.dungeonkeeper.common.object.GameObject;
import am.adrian.dungeonkeeper.common.object.Path;
import am.adrian.dungeonkeeper.common.object.Wall;
import am.adrian.dungeonkeeper.common.object.trait.CanFly;
import am.adrian.dungeonkeeper.common.object.trait.CanSwim;
import am.adrian.dungeonkeeper.common.object.trait.CanWalk;
import am.adrian.dungeonkeeper.game.tile.Water;
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
