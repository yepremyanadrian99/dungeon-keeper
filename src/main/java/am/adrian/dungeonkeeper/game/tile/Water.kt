package am.adrian.dungeonkeeper.game.tile

import am.adrian.dungeonkeeper.common.`object`.Path
import am.adrian.dungeonkeeper.common.coords.Coords
import am.adrian.dungeonkeeper.common.coords.ImmutableCoords

class Water(val coords: ImmutableCoords) : Path {

    override fun getCoords(): Coords = coords

    override fun getTexture(): String = "water.png"
}
