package am.adrian.dungeonkeeper.model.gameobject.tile

import am.adrian.dungeonkeeper.model.coords.Coords
import am.adrian.dungeonkeeper.model.coords.ImmutableCoords
import am.adrian.dungeonkeeper.model.gameobject.GameObject
import am.adrian.dungeonkeeper.model.gameobject.Path
import am.adrian.dungeonkeeper.model.player.Player

data class ClaimedPath(
    val coords: ImmutableCoords,
    val claimedBy: Player
) : Path {

    override fun getCoords(): Coords = coords

    override fun getTexture(): String = "claimed-by-${claimedBy.color}.png"

    override fun collides(gameObject: GameObject): Boolean = false
}
