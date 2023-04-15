package am.adrian.dungeonkeeper

import am.adrian.dungeonkeeper.config.GeneralConfig
import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main() {
    AnnotationConfigApplicationContext(GeneralConfig::class.java)
}
