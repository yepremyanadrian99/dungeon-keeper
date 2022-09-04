package am.adrian.dungeonkeeper.common.`object`

interface Collidable {

    fun collides(gameObject: GameObject): Boolean
}
