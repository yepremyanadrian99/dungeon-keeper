package am.adrian.dungeonkeeper.ui

import am.adrian.dungeonkeeper.ui.handler.gamepanel.GamePanelGraphicsHandler
import org.springframework.stereotype.Component
import java.awt.Color
import java.awt.Cursor
import java.awt.Graphics
import javax.swing.JPanel

@Component
class GameMapPanel(private val graphicsHandler: GamePanelGraphicsHandler) : JPanel() {

    init {
        cursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR)
        background = Color.YELLOW
        isVisible = true
    }

    override fun paint(g: Graphics?) = graphicsHandler.handle(g!!)
}
