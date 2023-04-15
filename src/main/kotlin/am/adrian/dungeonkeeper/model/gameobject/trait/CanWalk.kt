package am.adrian.dungeonkeeper.model.gameobject.trait

import am.adrian.dungeonkeeper.constant.Direction

interface CanWalk : CanMove {

    fun walk(dir: Direction)

    fun walkDelta(): Int
}
