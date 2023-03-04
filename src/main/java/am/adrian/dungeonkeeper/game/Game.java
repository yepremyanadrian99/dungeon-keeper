package am.adrian.dungeonkeeper.game;

import am.adrian.dungeonkeeper.game.controller.GameKeyController;
import am.adrian.dungeonkeeper.game.controller.GameRenderController;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

@RequiredArgsConstructor
public class Game extends JFrame {

    private final GameStateService stateService;
    private final GameRenderController renderController;
    private final GameKeyController keyController;

    @Scheduled(fixedRate = 1L)
    public void update() {
        if (!stateService.isRunning()) {
            repaint();
        }
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        keyController.handleEvent(e);
    }

    @Override
    public void paint(Graphics g) {
        renderController.paint(g);
    }
}
