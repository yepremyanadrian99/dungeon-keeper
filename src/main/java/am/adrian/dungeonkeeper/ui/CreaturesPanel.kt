package am.adrian.dungeonkeeper.ui

import am.adrian.dungeonkeeper.ui.handler.creaturespanel.CreaturesPanelGraphicsHandler
import org.springframework.stereotype.Component
import java.awt.Color
import java.awt.Cursor
import java.awt.Graphics
import javax.swing.JPanel

@Component
class CreaturesPanel : JPanel() {

    private val graphicsHandler = CreaturesPanelGraphicsHandler(this)

    init {

        cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        background = Color.BLACK
        isVisible = true
    }

    override fun paint(g: Graphics) {
        graphicsHandler.handle(g)
    }
}
