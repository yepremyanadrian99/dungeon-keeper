package am.adrian.dungeonkeeper.model.gameobject

import am.adrian.dungeonkeeper.model.coords.Coords
import am.adrian.dungeonkeeper.model.gameobject.trait.CanCollide

interface GameObject : CanCollide {

    fun getCoords(): Coords

    fun getTexture(): String
}
