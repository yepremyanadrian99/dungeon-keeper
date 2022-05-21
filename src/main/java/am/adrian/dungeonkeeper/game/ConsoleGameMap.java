package am.adrian.dungeonkeeper.game;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
@Getter
public class ConsoleGameMap {

    private final Collection<ConsoleGameObject> objects = new ArrayList<>();

    private final int width;
    private final int height;

    public void addObject(ConsoleGameObject object) {
        objects.add(object);
    }

    public void removeObject(ConsoleGameObject object) {
        objects.remove(object);
    }
}
