package am.adrian.dungeonkeeper.model.coords

data class MutableCoords(
    private var x: Int = 0,
    private var y: Int = 0
) : Coords {

    override fun x(): Int = x

    override fun y(): Int = y

    fun x(x: Int) {
        this.x = x
    }

    fun y(y: Int) {
        this.y = y
    }

    fun incX(delta: Int) {
        x += delta
    }

    fun incY(delta: Int) {
        y += delta
    }
}
