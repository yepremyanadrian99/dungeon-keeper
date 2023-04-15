package am.adrian.dungeonkeeper.model.gameobject.trait

interface HasMood {

    fun getMood(): Mood

    fun setMood(mood: Mood)

    enum class Mood(val symbol: Char) {
        HAPPY('H'),
        UNHAPPY('U'),
        ANGRY('A');
    }
}
