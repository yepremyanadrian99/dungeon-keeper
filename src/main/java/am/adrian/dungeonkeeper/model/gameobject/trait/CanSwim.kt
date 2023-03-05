package am.adrian.dungeonkeeper.model.gameobject.trait

import am.adrian.dungeonkeeper.constant.Direction

interface CanSwim : CanMove {

    fun swim(dir: Direction)

    fun swimDelta(): Int
}
