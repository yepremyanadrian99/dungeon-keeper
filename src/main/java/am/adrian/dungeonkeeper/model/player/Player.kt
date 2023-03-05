package am.adrian.dungeonkeeper.model.player

import am.adrian.dungeonkeeper.constant.PlayerColor

data class Player(
    val id: String,
    val name: String,
    val color: PlayerColor,
    val isAi: Boolean
)
