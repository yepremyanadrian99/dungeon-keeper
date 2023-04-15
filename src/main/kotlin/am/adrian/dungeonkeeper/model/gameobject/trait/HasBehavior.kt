package am.adrian.dungeonkeeper.model.gameobject.trait

import am.adrian.dungeonkeeper.behavior.BehaviorTree

interface HasBehavior {

    fun getBehaviorTree(): BehaviorTree

    fun executeBehavior() {
        getBehaviorTree().execute()
    }
}
