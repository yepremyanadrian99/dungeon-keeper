package am.adrian.dungeonkeeper.model.gameobject.tile.path

import am.adrian.dungeonkeeper.model.gameobject.GameObject

interface Path : GameObject {

    override fun collides(gameObject: GameObject): Boolean = false
}
