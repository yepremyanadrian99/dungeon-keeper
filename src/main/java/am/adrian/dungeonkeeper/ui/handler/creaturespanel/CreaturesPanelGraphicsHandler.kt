package am.adrian.dungeonkeeper.ui.handler.creaturespanel

import am.adrian.dungeonkeeper.ui.CreaturesPanel
import am.adrian.dungeonkeeper.ui.handler.UIEventHandler
import java.awt.Color
import java.awt.Graphics

class CreaturesPanelGraphicsHandler(private val panel: CreaturesPanel) : UIEventHandler<Graphics> {

    override fun handle(event: Graphics) {
        event.color = Color.BLUE
        event.fillRect(0, 0, panel.width, panel.height)
    }
}
