package am.adrian.dungeonkeeper.model.health

data class Health(var level: Int) {

    fun incLevel(amount: Int) {
        level += amount
    }
}
