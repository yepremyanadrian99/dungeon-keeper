package am.adrian.dungeonkeeper.game;

import am.adrian.dungeonkeeper.constant.GameState;
import am.adrian.dungeonkeeper.renderer.GameMapRenderer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConsoleGame implements Runnable {

    private final GameMapRenderer renderer;
    private final GameStateService stateService;

    public void start() {
        System.out.println("Game started");
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
