package am.adrian.dungeonkeeper;

import am.adrian.dungeonkeeper.config.GeneralConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(GeneralConfig.class);
    }
}
