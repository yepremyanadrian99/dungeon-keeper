package am.adrian.dungeonkeeper.game;

import am.adrian.dungeonkeeper.common.ConsoleGameObject;
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

    public void addObjects(Collection<? extends ConsoleGameObject> objectsToAdd) {
        objects.addAll(objectsToAdd);
    }

    public void removeObject(ConsoleGameObject object) {
        objects.remove(object);
    }
}
