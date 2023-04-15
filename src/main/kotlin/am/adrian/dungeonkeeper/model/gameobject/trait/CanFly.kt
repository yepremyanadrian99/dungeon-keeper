package am.adrian.dungeonkeeper.model.gameobject.trait

import am.adrian.dungeonkeeper.constant.Direction

interface CanFly : CanMove {

    fun fly(dir: Direction)
}
