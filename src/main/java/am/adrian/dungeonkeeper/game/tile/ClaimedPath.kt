package am.adrian.dungeonkeeper.game.tile

import am.adrian.dungeonkeeper.common.`object`.GameObject
import am.adrian.dungeonkeeper.common.`object`.path.Path
import am.adrian.dungeonkeeper.common.coords.Coords
import am.adrian.dungeonkeeper.common.coords.ImmutableCoords
import am.adrian.dungeonkeeper.common.player.Player

data class ClaimedPath(
    val coords: ImmutableCoords,
    val claimedBy: Player,
    private val texture: String = "claimed-by-${claimedBy.color}"
) : Path {

    override fun getCoords(): Coords = coords

    override fun getTexture(): String = texture

    override fun collides(gameObject: GameObject): Boolean = false
}
