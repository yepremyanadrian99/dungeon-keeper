package am.adrian.dungeonkeeper.helper

import am.adrian.dungeonkeeper.model.gameobject.GameObject

object CollisionChecker {

    @JvmStatic
    fun objectsCollide(object1: GameObject, object2: GameObject): Boolean {
        return object1.collides(object2) || object2.collides(object1)
    }
}
