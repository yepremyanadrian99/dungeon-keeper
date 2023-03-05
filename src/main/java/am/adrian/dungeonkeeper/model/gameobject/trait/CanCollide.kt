package am.adrian.dungeonkeeper.model.gameobject.trait

import am.adrian.dungeonkeeper.model.gameobject.GameObject

interface CanCollide {

    fun collides(gameObject: GameObject): Boolean
}
