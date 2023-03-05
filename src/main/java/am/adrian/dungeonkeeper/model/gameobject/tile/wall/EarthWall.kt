package am.adrian.dungeonkeeper.model.gameobject.tile.wall

import am.adrian.dungeonkeeper.model.coords.Coords
import am.adrian.dungeonkeeper.model.coords.ImmutableCoords
import am.adrian.dungeonkeeper.model.gameobject.GameObject

private const val TEXTURE = "earth.png"

class EarthWall(private val coords: ImmutableCoords) : Wall {

    constructor(x: Int, y: Int) : this(ImmutableCoords(x, y))

    override fun getCoords(): Coords = coords

    override fun getTexture(): String = TEXTURE

    override fun collides(gameObject: GameObject): Boolean = true
}
