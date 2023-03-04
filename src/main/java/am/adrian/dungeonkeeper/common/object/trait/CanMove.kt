package am.adrian.dungeonkeeper.common.`object`.trait

import am.adrian.dungeonkeeper.common.constant.Direction

interface CanMove {

    fun move(dir: Direction, delta: Int)
}
