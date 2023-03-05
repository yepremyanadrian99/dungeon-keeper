package am.adrian.dungeonkeeper.model.gameobject.room

import am.adrian.dungeonkeeper.model.gameobject.GameObject
import am.adrian.dungeonkeeper.model.gameobject.trait.CanBeTraded

interface Room : GameObject, CanBeTraded {

    fun getId(): Int

    override fun collides(gameObject: GameObject): Boolean = false
}
