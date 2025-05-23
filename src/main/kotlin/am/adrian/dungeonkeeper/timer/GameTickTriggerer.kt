package am.adrian.dungeonkeeper.timer

import am.adrian.dungeonkeeper.observer.Observable
import am.adrian.dungeonkeeper.observer.Observer
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class GameTickTriggerer : Observable {

    override val observers: MutableList<Observer> = mutableListOf()

    @Scheduled(fixedRate = 1L, timeUnit = TimeUnit.SECONDS)
    fun triggerObservers() {
        observers.forEach { it.updated(this) }
    }

    override fun addObserver(o: Observer) {
        observers.add(o)
    }
}
