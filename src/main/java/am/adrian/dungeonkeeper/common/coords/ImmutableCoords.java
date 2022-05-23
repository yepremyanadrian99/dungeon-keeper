package am.adrian.dungeonkeeper.common.coords;

public record ImmutableCoords(int x, int y) implements Coords {

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
