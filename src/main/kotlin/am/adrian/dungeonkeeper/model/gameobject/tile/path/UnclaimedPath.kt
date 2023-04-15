package am.adrian.dungeonkeeper.model.gameobject.tile.path

import am.adrian.dungeonkeeper.model.coords.Coords
import am.adrian.dungeonkeeper.model.coords.ImmutableCoords

private const val TEXTURE: String = "dirt.jpg"

class UnclaimedPath(private val coords: ImmutableCoords) : Path {

    constructor(x: Int, y: Int) : this(ImmutableCoords(x, y))

    override fun getCoords(): Coords = coords

    override fun getTexture(): String = TEXTURE
}
