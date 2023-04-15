package am.adrian.dungeonkeeper.model.gameobject.trait

import am.adrian.dungeonkeeper.model.player.Player

interface BelongsTo {

    fun belongsTo(): Player
}
