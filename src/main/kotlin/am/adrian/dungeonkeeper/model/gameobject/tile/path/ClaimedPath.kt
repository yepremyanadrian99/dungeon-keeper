package am.adrian.dungeonkeeper.model.gameobject.tile.path

import am.adrian.dungeonkeeper.model.coords.Coords
import am.adrian.dungeonkeeper.model.coords.ImmutableCoords
import am.adrian.dungeonkeeper.model.player.Player

data class ClaimedPath(
    private val coords: ImmutableCoords,
    private val claimedBy: Player
) : Path {

    constructor(x: Int, y: Int, claimedBy: Player) : this(ImmutableCoords(x, y), claimedBy)

    override fun getCoords(): Coords = coords

    override fun getTexture(): String = "claimed-by-${claimedBy.color}.png"
}
