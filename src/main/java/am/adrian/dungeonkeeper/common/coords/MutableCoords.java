package am.adrian.dungeonkeeper.common.coords;

import lombok.Setter;

@Setter
public class MutableCoords implements Coords {

    private int x;
    private int y;

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void incX() {
        ++x;
    }

    public void incY() {
        ++y;
    }

    public void decX() {
        --x;
    }

    public void decY() {
        --y;
    }
}
