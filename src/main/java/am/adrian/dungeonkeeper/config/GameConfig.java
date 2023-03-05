package am.adrian.dungeonkeeper.config;

import am.adrian.dungeonkeeper.game.GameMap;
import am.adrian.dungeonkeeper.model.coords.ImmutableCoords;
import am.adrian.dungeonkeeper.model.gameobject.character.Goblin;
import am.adrian.dungeonkeeper.model.gameobject.tile.Impenetrable;
import am.adrian.dungeonkeeper.model.health.Health;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

import static am.adrian.dungeonkeeper.helper.ObjectGenerator.betweenPoints;

@Configuration
@RequiredArgsConstructor
public class GameConfig {

    private final GameMap map;

    @Value("${creatures.goblin.width}")
    private int goblinWidth;

    @Value("${creatures.goblin.height}")
    private int goblinHeight;

    @Value("${creatures.goblin.maxHealth}")
    private int goblinMaxHealth;

    @PostConstruct
    public void initObjects() {
        final int width = map.getWidth();
        final int height = map.getHeight();
        map.addObjects(betweenPoints(new ImmutableCoords(0, 0), new ImmutableCoords(width - 1, 0), Impenetrable::new));
        map.addObjects(betweenPoints(new ImmutableCoords(0, 0), new ImmutableCoords(0, height - 1), Impenetrable::new));
        map.addObjects(betweenPoints(0, height - 1, width - 1, height - 1, Impenetrable::new));
        map.addObjects(betweenPoints(width - 1, 0, width - 1, height - 1, Impenetrable::new));
    }

    @PostConstruct
    public void initCharacters() {
        final var goblin = new Goblin(new Health(goblinMaxHealth), goblinWidth, goblinHeight);
        goblin.getCoords().x(10);
        goblin.getCoords().y(4);
        map.addCreature(goblin);
    }
}
