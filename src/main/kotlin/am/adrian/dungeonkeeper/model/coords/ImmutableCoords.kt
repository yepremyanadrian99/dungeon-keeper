package am.adrian.dungeonkeeper.model.coords

data class ImmutableCoords(
    private val x: Int,
    private val y: Int
) : Coords {

    override fun x(): Int = x

    override fun y(): Int = y
}
