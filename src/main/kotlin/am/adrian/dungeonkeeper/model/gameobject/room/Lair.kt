package am.adrian.dungeonkeeper.model.gameobject.room

import am.adrian.dungeonkeeper.model.coords.Coords

private const val ID: Int = 1
private const val TEXTURE: String = "lair.png"
private const val PRICE: Int = 200

class Lair(private val coords: Coords) : Room {

    override fun getId(): Int = ID

    override fun getCoords(): Coords = coords

    override fun getTexture(): String = TEXTURE

    override fun getPrice(): Int = PRICE
}
