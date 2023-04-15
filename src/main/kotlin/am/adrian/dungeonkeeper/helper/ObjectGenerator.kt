package am.adrian.dungeonkeeper.helper

import am.adrian.dungeonkeeper.model.coords.ImmutableCoords
import am.adrian.dungeonkeeper.model.gameobject.GameObject
import java.util.function.BiFunction

fun <T : GameObject> betweenPoints(
    p1: ImmutableCoords,
    p2: ImmutableCoords,
    constructor: BiFunction<Int, Int, T>
): Collection<T> = betweenPoints(p1.x(), p1.y(), p2.x(), p2.y(), constructor)

fun <T : GameObject> betweenPoints(
    p1X: Int,
    p1Y: Int,
    p2X: Int,
    p2Y: Int,
    constructor: BiFunction<Int, Int, T>
): Collection<T> {
    val result: MutableCollection<T> = ArrayList()
    for (i in p1Y..p2Y) {
        for (j in p1X..p2X) {
            result.add(constructor.apply(j, i))
        }
    }
    return result
}
