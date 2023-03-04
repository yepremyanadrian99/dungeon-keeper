package am.adrian.dungeonkeeper.common.`object`.trait

import am.adrian.dungeonkeeper.common.`object`.GameObject

interface CanCollide {

    fun collides(gameObject: GameObject): Boolean
}
