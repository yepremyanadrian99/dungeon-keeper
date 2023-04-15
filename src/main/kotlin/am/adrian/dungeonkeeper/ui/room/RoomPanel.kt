package am.adrian.dungeonkeeper.ui.room

import am.adrian.dungeonkeeper.ui.room.handler.RoomPanelGraphicsHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.awt.Color
import java.awt.Cursor
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JPanel

@Component
class RoomPanel(
    private val graphicsHandler: RoomPanelGraphicsHandler,
    @Value("\${roomPanel.width}") width: Int,
    @Value("\${roomPanel.height}") height: Int
) : JPanel() {

    init {
        size = Dimension(width, height)
        cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)
        background = Color.BLACK
        isVisible = true
    }

    override fun paint(g: Graphics) = graphicsHandler.handle(g)
}
