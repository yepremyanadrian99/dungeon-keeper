package am.adrian.dungeonkeeper.ui.gamemap

import am.adrian.dungeonkeeper.ui.gamemap.handler.GameMapPanelGraphicsHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.awt.Color
import java.awt.Cursor
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JPanel

@Component
class GameMapPanel(
    private val graphicsHandler: GameMapPanelGraphicsHandler,
    @Value("\${gameMapPanel.width}") width: Int,
    @Value("\${gameMapPanel.height}") height: Int
) : JPanel() {

    init {
        size = Dimension(width, height)
        cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        background = Color.BLACK
        isVisible = true
    }

    override fun paint(g: Graphics) = graphicsHandler.handle(g)
}
