package am.adrian.dungeonkeeper.ui.creature.handler

import am.adrian.dungeonkeeper.ui.PanelEventHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.awt.Color
import java.awt.Graphics

@Component
class CreaturePanelGraphicsHandler(
    @Value("\${creaturePanel.width}") private val width: Int,
    @Value("\${creaturePanel.height}") private val height: Int
) : PanelEventHandler<Graphics> {

    override fun handle(event: Graphics) {
        event.color = Color.BLUE
        event.fillRect(0, 0, width, height)
    }
}
