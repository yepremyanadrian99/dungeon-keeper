package am.adrian.dungeonkeeper.game.handler

import am.adrian.dungeonkeeper.common.handler.GameEventHandler
import am.adrian.dungeonkeeper.game.GameMap
import am.adrian.dungeonkeeper.helper.ResourceHelper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.BufferedImage

@Component
class GameGraphicsHandler(
    private val map: GameMap,
    private val resourceHelper: ResourceHelper,
    @Value("\${window.offsetX}") private val offsetX: Int,
    @Value("\${window.offsetY}") private val offsetY: Int,
    @Value("\${window.cell.size}") private val cellSize: Int,
    @Value("\${window.map.outlines}") private val outlines: Boolean
) : GameEventHandler<Graphics> {

    override fun handle(event: Graphics) {
        val bufferedMap = BufferedImage(
            cellSize * map.width,
            cellSize * map.height,
            BufferedImage.TYPE_INT_RGB
        )
        val g2d = bufferedMap.createGraphics()
        drawLand(g2d)
        drawCreatures(g2d)
        if (outlines) {
            drawOutlines(g2d)
        }

        val g2dComponent = event as Graphics2D
        g2dComponent.drawImage(bufferedMap, null, offsetX, offsetY)
    }

    private fun drawLand(g2d: Graphics2D) {
        for (i in map.objectMap.indices) {
            for (j in map.objectMap[i].indices) {
                val gameObject = map.objectMap[i][j] ?: continue
                val image: BufferedImage = resourceHelper.loadBufferedImage(gameObject.texture)
                g2d.drawImage(
                    image,
                    cellSize * gameObject.coords.x(),
                    cellSize * gameObject.coords.y(),
                    cellSize,
                    cellSize,
                    null
                )
            }
        }
    }

    private fun drawCreatures(g2d: Graphics2D) {
        for (creature in map.creatures) {
            val image: BufferedImage = resourceHelper.loadBufferedImage(creature.texture)
            g2d.drawImage(
                image,
                creature.coords.x(),
                creature.coords.y(),
                creature.getWidth(),
                creature.getHeight(),
                null
            )
        }
    }

    private fun drawOutlines(g2d: Graphics2D) {
        for (i in 0 until map.height) {
            for (j in 0 until map.width) {
                g2d.color = Color.white
                g2d.drawLine(j * cellSize, 0, j * cellSize, map.height * cellSize)
                g2d.drawLine(0, i * cellSize, cellSize * map.width, i * cellSize)
            }
        }
    }
}
