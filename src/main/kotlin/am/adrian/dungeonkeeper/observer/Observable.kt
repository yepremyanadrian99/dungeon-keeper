package am.adrian.dungeonkeeper.observer

interface Observable {
    val observers: MutableList<Observer>
    fun addObserver(o: Observer)
}
