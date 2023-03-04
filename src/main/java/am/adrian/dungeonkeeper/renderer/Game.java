package am.adrian.dungeonkeeper.renderer;

import am.adrian.dungeonkeeper.common.constant.Direction;
import am.adrian.dungeonkeeper.common.coords.Coords;
import am.adrian.dungeonkeeper.common.object.Creature;
import am.adrian.dungeonkeeper.common.object.GameObject;
import am.adrian.dungeonkeeper.game.GameMap;
import am.adrian.dungeonkeeper.game.GameStateService;
import am.adrian.dungeonkeeper.game.MoveValidator;
import am.adrian.dungeonkeeper.game.character.Goblin;
import am.adrian.dungeonkeeper.helper.ResourceHelper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static java.awt.event.KeyEvent.KEY_TYPED;

@RequiredArgsConstructor
public class Game extends JFrame {

    private static final Logger logger = LogManager.getLogger(Game.class);

    private final GameStateService stateService;
    private final GameMap map;
    private final MoveValidator moveValidator;
    private final ResourceHelper resourceHelper;
    private final int offsetX;
    private final int offsetY;
    private final int cellSize;
    private final boolean outlines;

    @Scheduled(fixedRate = 1L)
    public void update() {
        if (!stateService.isRunning()) {
            repaint();
        }
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        if (e.getID() != KEY_TYPED) {
            return;
        }
        final var goblin = ((Goblin) map.getCreatures().get(0));
        final var gameObject = creatureStandsOn(goblin.getCoords());
        logger.info("Goblin is on: " + gameObject.getTexture());

        if (e.getKeyChar() == 'w') {
            goblin.walk(Direction.UP);
        } else if (e.getKeyChar() == 's') {
            goblin.walk(Direction.DOWN);
        } else if (e.getKeyChar() == 'a') {
            goblin.walk(Direction.LEFT);
        } else if (e.getKeyChar() == 'd') {
            goblin.walk(Direction.RIGHT);
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
        if (outlines) {
            drawOutlines(g2d);
        }

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
                    creature.getCoords().getX(),
                    creature.getCoords().getY(),
                    creature.getWidth(),
                    creature.getHeight(),
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

    private GameObject creatureStandsOn(Coords coords) {
        return map.getObjectMap()[coords.getY() / cellSize][coords.getX() / cellSize];
    }
}
