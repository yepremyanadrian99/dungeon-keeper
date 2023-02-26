package am.adrian.dungeonkeeper.renderer;

import am.adrian.dungeonkeeper.common.handler.Handler;
import am.adrian.dungeonkeeper.common.object.Creature;
import am.adrian.dungeonkeeper.game.GameMap;
import am.adrian.dungeonkeeper.game.GameStateService;
import am.adrian.dungeonkeeper.helper.ResourceHelper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@RequiredArgsConstructor
public class GameRenderer2D extends JFrame implements Handler {

    private static final Logger logger = LogManager.getLogger(GameRenderer2D.class);

    private final GameStateService stateService;
    private final GameMap map;
    private final ResourceHelper resourceHelper;
    private final int offsetX;
    private final int offsetY;
    private final int cellSize;

    @Override
    public void handle() {
        logger.debug("Handle method called");
        logger.debug("Exiting handle method");
    }

    @Scheduled(fixedDelay = 1L)
    public void update() {
        if (!stateService.isFinished()) {
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        final var bufferedMap = new BufferedImage(
                cellSize * map.getWidth(),
                cellSize * map.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );
        final Graphics2D g2d = bufferedMap.createGraphics();
        drawLand(g2d);
        drawCreatures(g2d);
        drawOutlines(g2d);

        final Graphics2D g2dComponent = (Graphics2D) g;
        g2dComponent.drawImage(bufferedMap, null, offsetX, offsetY);
    }

    private void drawLand(Graphics2D g2d) {
        for (int i = 0; i < map.getObjectMap().length; ++i) {
            for (int j = 0; j < map.getObjectMap()[i].length; ++j) {
                final var gameObject = map.getObjectMap()[i][j];
                final var image = resourceHelper.loadBufferedImage(gameObject.getTexture());
                g2d.drawImage(
                        image,
                        cellSize * gameObject.getCoords().getX(),
                        cellSize * gameObject.getCoords().getY(),
                        cellSize,
                        cellSize,
                        null
                );
            }
        }
    }

    private void drawCreatures(Graphics2D g2d) {
        for (Creature creature : map.getCreatures()) {
            final var image = resourceHelper.loadBufferedImage(creature.getTexture());
            g2d.drawImage(
                    image,
                    creature.getCoords().getX() * cellSize,
                    creature.getCoords().getY() * cellSize,
                    cellSize,
                    cellSize,
                    null
            );
        }
    }

    private void drawOutlines(Graphics2D g2d) {
        for (int i = 0; i < map.getHeight(); ++i) {
            for (int j = 0; j < map.getWidth(); ++j) {
                g2d.setColor(Color.white);
                g2d.drawLine(j * cellSize, 0, j * cellSize, map.getHeight() * cellSize);
                g2d.drawLine(0, i * cellSize, cellSize * map.getWidth(), i * cellSize);
            }
        }
    }
}
