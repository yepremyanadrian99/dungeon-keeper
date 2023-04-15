package am.adrian.dungeonkeeper.model.gameobject.trait

import am.adrian.dungeonkeeper.model.health.Health

interface HasHealth {

    fun getHealth(): Health

    fun hasHealth(): Boolean {
        return getHealth().level != 0
    }
}
