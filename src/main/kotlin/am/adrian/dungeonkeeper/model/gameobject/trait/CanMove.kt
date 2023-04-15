package am.adrian.dungeonkeeper.model.gameobject.trait

import am.adrian.dungeonkeeper.constant.Direction

interface CanMove {

    fun move(dir: Direction, delta: Int)
}
