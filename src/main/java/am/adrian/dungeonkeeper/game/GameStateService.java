package am.adrian.dungeonkeeper.game;

import am.adrian.dungeonkeeper.constant.GameState;
import org.springframework.stereotype.Service;

@Service
public class GameStateService {

    private volatile GameState state = GameState.NOT_STARTED;

    public synchronized void setState(GameState state) {
        this.state = state;
    }

    public GameState getState() {
        return state;
    }

    public boolean isFinished() {
        return state != null && state != GameState.NOT_STARTED && state != GameState.FINISHED;
    }
}
