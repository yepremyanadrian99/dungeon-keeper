package am.adrian.dungeonkeeper.model.gameobject.character;

import am.adrian.dungeonkeeper.constant.Direction;
import am.adrian.dungeonkeeper.model.coords.MutableCoords;
import am.adrian.dungeonkeeper.model.gameobject.Creature;
import am.adrian.dungeonkeeper.model.gameobject.GameObject;
import am.adrian.dungeonkeeper.model.gameobject.trait.*;
import am.adrian.dungeonkeeper.model.health.Health;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
@Getter
@Setter
public class Goblin implements Creature, HasHealth, CanWalk, CanSwim, HasEmotion, CanLevel {

    private static final String TEXTURE = "goblin.png";

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
