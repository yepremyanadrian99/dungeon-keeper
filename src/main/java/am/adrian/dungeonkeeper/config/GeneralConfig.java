package am.adrian.dungeonkeeper.config;

import am.adrian.dungeonkeeper.Application;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackageClasses = Application.class)
@PropertySource(value = "classpath:application.properties")
public class GeneralConfig {
}
