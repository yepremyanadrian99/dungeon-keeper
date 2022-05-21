package am.adrian.dungeonkeeper.game;

import am.adrian.dungeonkeeper.common.Handler;
import am.adrian.dungeonkeeper.common.HandlerContainer;
import am.adrian.dungeonkeeper.constant.GameState;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;

@RequiredArgsConstructor
public class ConsoleGame implements HandlerContainer {

    private static final Logger logger = LogManager.getLogger(ConsoleGame.class);

    private final Collection<Handler> handlers = new ArrayList<>();

    private final GameStateService stateService;
    private final ExecutorService handlerExecutor;

    @Override
    public void registerHandler(Handler handler) {
        handlers.add(handler);
    }

    public void start() {
        logger.debug("Game started");
        stateService.setState(GameState.STARTED);
        startHandlers();
    }

    public void finish() {
        logger.debug("Finishing game");
        stateService.setState(GameState.FINISHED);
    }

    public void startHandlers() {
        if (stateService.isFinished()) {
            return;
        }
        handlers.forEach(handler -> handlerExecutor.submit(handler::handle));
        handlerExecutor.shutdown();
    }
}
