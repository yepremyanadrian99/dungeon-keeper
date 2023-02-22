package am.adrian.dungeonkeeper.renderer;

import am.adrian.dungeonkeeper.common.handler.Handler;
import am.adrian.dungeonkeeper.common.object.Creature;
import am.adrian.dungeonkeeper.game.GameMap;
import am.adrian.dungeonkeeper.game.GameStateService;
import am.adrian.dungeonkeeper.helper.ResourceHelper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
    @SneakyThrows(IOException.class)
    public void paint(Graphics g) {
        for (int i = 0; i < map.getObjectMap().length; ++i) {
            for (int j = 0; j < map.getObjectMap()[i].length; ++j) {
                final var gameObject = map.getObjectMap()[i][j];
                final var file = resourceHelper.loadTexture(gameObject.getTexture());
                final BufferedImage image = ImageIO.read(file);
                g.drawImage(
                        image,
                        offsetX + cellSize * gameObject.getCoords().getX(),
                        offsetY + cellSize * gameObject.getCoords().getY(),
                        cellSize,
                        cellSize,
                        null
                );
//                g.setColor(gameObject.getColor());
//                g.fillRect(
//                        offsetX + cellSize * gameObject.getCoords().getX(),
//                        offsetY + cellSize * gameObject.getCoords().getY(),
//                        cellSize,
//                        cellSize
//                );
            }
        }
        for (int i = 0; i < map.getHeight(); ++i) {
            for (int j = 0; j < map.getWidth(); ++j) {
                g.setColor(Color.white);
                g.drawLine(offsetX + j * cellSize, offsetY, offsetX + j * cellSize, offsetY + map.getHeight() * cellSize);
                g.drawLine(offsetX, offsetY + i * cellSize, offsetX + cellSize * map.getWidth(), offsetY + i * cellSize);
            }
        }
        for (Creature creature : map.getCreatures()) {
            final var file = resourceHelper.loadTexture(creature.getTexture());
            final BufferedImage image = ImageIO.read(file);
            g.drawImage(
                    image,
                    offsetX + creature.getCoords().getX() * cellSize,
                    offsetY + creature.getCoords().getY() * cellSize,
                    cellSize,
                    cellSize,
                    null
            );
        }
    }
}
