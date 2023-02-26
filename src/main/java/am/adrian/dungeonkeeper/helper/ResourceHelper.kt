package am.adrian.dungeonkeeper.helper

import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Component
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

@Component
class ResourceHelper(private val resourceLoader: ResourceLoader) {

    private val cachedImages: MutableMap<String, BufferedImage> = mutableMapOf()

    fun loadBufferedImage(texture: String): BufferedImage =
        cachedImages.computeIfAbsent(texture) { ImageIO.read(loadTexture(it)) }

    private fun loadTexture(texture: String): File =
        resourceLoader.getResource("classpath:icons/$texture").file
}
