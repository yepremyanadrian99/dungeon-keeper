package am.adrian.dungeonkeeper.helper

import am.adrian.dungeonkeeper.constant.PlayerColor
import am.adrian.dungeonkeeper.model.player.Player

class Players {

    companion object {

        val NEUTRAL_PLAYER = Player("neutral", "Neutral Player", PlayerColor.GRAY, true)
    }
}
