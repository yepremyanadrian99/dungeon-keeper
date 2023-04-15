package am.adrian.dungeonkeeper.model.gameobject.trait

interface CanAttack {

    fun attack(other: HasHealth)
}
