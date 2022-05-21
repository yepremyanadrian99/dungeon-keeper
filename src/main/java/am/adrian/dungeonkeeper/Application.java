package am.adrian.dungeonkeeper;

import am.adrian.dungeonkeeper.config.GeneralConfig;
import am.adrian.dungeonkeeper.game.ConsoleGame;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        final ApplicationContext context = new AnnotationConfigApplicationContext(GeneralConfig.class);
        final ConsoleGame game = context.getBean(ConsoleGame.class);
        game.start();
    }
}
