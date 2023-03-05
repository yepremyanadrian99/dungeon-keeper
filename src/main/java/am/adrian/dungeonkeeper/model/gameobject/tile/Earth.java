package am.adrian.dungeonkeeper.model.gameobject.tile;

import am.adrian.dungeonkeeper.model.coords.ImmutableCoords;
import am.adrian.dungeonkeeper.model.gameobject.GameObject;
import am.adrian.dungeonkeeper.model.gameobject.Wall;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class Earth implements Wall {

    private static final String TEXTURE = "earth.png";

    private final ImmutableCoords coords;

    public Earth(int x, int y) {
        this.coords = new ImmutableCoords(x, y);
    }

    @Override
    public ImmutableCoords getCoords() {
        return coords;
    }

    @Override
    public String getTexture() {
        return TEXTURE;
    }

    @Override
    public boolean collides(@NotNull GameObject gameObject) {
        return true;
    }
}
