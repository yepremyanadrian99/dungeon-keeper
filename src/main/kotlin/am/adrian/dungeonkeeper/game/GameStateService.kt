package am.adrian.dungeonkeeper.game

import am.adrian.dungeonkeeper.constant.GameState
import org.springframework.stereotype.Service

@Service
class GameStateService {

    private var state: GameState = GameState.NOT_STARTED

    fun setState(state: GameState) {
        this.state = state
    }

    fun isStarted(): Boolean {
        return state == GameState.STARTED
    }
}
