package am.adrian.dungeonkeeper.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@EnableScheduling
@ComponentScan(basePackages = ["am.adrian.dungeonkeeper"])
@PropertySource(value = ["classpath:application.properties"])
open class GeneralConfig
