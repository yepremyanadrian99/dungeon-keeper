package am.adrian.dungeonkeeper.game.tile

import am.adrian.dungeonkeeper.common.`object`.GameObject
import am.adrian.dungeonkeeper.common.`object`.Path
import am.adrian.dungeonkeeper.common.coords.Coords
import am.adrian.dungeonkeeper.common.coords.ImmutableCoords
import am.adrian.dungeonkeeper.common.player.Player

data class ClaimedPath(
    val coords: ImmutableCoords,
    val claimedBy: Player
) : Path {

    override fun getCoords(): Coords = coords

    override fun getTexture(): String = "claimed-by-${claimedBy.color}.png"

    override fun collides(gameObject: GameObject): Boolean = false
}
