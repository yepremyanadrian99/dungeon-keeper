package am.adrian.dungeonkeeper.model.gameobject.tile

import am.adrian.dungeonkeeper.model.coords.Coords
import am.adrian.dungeonkeeper.model.coords.ImmutableCoords
import am.adrian.dungeonkeeper.model.gameobject.Path

private const val TEXTURE: String = "water.png"

class Water(val coords: ImmutableCoords) : Path {

    override fun getCoords(): Coords = coords

    override fun getTexture(): String = TEXTURE
}
