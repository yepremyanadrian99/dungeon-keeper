package am.adrian.dungeonkeeper.common.`object`

import am.adrian.dungeonkeeper.common.constant.Direction

interface Movable {

    fun move(dir: Direction, delta: Int)
}
