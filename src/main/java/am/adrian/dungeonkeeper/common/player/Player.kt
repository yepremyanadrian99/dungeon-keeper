package am.adrian.dungeonkeeper.common.player

import am.adrian.dungeonkeeper.common.constant.PlayerColor

data class Player(
    val id: String,
    val name: String,
    val color: PlayerColor,
    val isComputer: Boolean
)
