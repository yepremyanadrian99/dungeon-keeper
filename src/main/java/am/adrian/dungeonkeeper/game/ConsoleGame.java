package am.adrian.dungeonkeeper.game;

import am.adrian.dungeonkeeper.constant.GameState;
import am.adrian.dungeonkeeper.renderer.GameMapRenderer;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RequiredArgsConstructor
public class ConsoleGame implements Runnable {

    private static final Logger logger = LogManager.getLogger(ConsoleGame.class);

    private final GameMapRenderer renderer;
    private final GameStateService stateService;

    public void start() {
        logger.info("Game started");
        stateService.setState(GameState.STARTED);
        run();
    }

    @Override
    public void run() {
        while (!stateService.isFinished()) {
            renderer.render();
        }
    }
}
