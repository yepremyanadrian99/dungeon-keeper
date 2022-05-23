package am.adrian.dungeonkeeper.helper;

import am.adrian.dungeonkeeper.common.coords.ImmutableCoords;
import am.adrian.dungeonkeeper.common.object.GameObject;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiFunction;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectGenerator {

    public static <T extends GameObject> Collection<T> betweenPoints(ImmutableCoords p1, ImmutableCoords p2,
                                                                     BiFunction<Integer, Integer, T> constructor) {
        return betweenPoints(p1.x(), p1.y(), p2.x(), p2.y(), constructor);
    }

    public static <T extends GameObject> Collection<T> betweenPoints(int p1X, int p1Y, int p2X, int p2Y,
                                                                     BiFunction<Integer, Integer, T> constructor) {
        final Collection<T> result = new ArrayList<>();
        for (int i = p1Y; i <= p2Y; ++i) {
            for (int j = p1X; j <= p2X; ++j) {
                result.add(constructor.apply(j, i));
            }
        }
        return result;
    }
}
