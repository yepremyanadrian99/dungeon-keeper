package am.adrian.dungeonkeeper.ui.creature

import am.adrian.dungeonkeeper.ui.creature.handler.CreaturePanelGraphicsHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.awt.Color
import java.awt.Cursor
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JPanel

@Component
class CreaturePanel(
    private val graphicsHandler: CreaturePanelGraphicsHandler,
    @Value("\${creaturePanel.width}") width: Int,
    @Value("\${creaturePanel.height}") height: Int
) : JPanel() {

    init {
        size = Dimension(width, height)
        cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)
        background = Color.BLACK
        isVisible = true
    }

    override fun paint(g: Graphics) {
        graphicsHandler.handle(g)
    }
}
