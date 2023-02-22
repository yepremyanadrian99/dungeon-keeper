package am.adrian.dungeonkeeper.helper

import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Component
import java.io.File

@Component
class ResourceHelper(private val resourceLoader: ResourceLoader) {

    val cachedTextures: MutableMap<String, File> = mutableMapOf()

    fun loadTexture(texture: String): File {
        return cachedTextures.computeIfAbsent(texture) {
            resourceLoader.getResource("classpath:icons/$it").file
        }
    }
}
