package am.adrian.dungeonkeeper.game;

import am.adrian.dungeonkeeper.common.constant.GameState;
import org.springframework.stereotype.Service;

@Service
public class GameStateService {

    private volatile GameState state = GameState.NOT_STARTED;

    public synchronized void setState(GameState state) {
        this.state = state;
    }

    public synchronized boolean isRunning() {
        return !(state == null || state == GameState.NOT_STARTED || state == GameState.FINISHED);
    }
}
