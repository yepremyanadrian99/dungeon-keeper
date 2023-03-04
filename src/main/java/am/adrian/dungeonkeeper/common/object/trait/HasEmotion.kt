package am.adrian.dungeonkeeper.common.`object`.trait

interface HasEmotion {

    fun getMood(): Mood

    fun setMood(mood: Mood)

    enum class Mood(val symbol: Char) {
        HAPPY('H'),
        UNHAPPY('U'),
        ANGRY('A');
    }
}
