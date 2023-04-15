package am.adrian.dungeonkeeper.ui.room.handler

import am.adrian.dungeonkeeper.ui.PanelEventHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.awt.Color
import java.awt.Graphics

@Component
class RoomPanelGraphicsHandler(
    @Value("\${roomPanel.width}") private val width: Int,
    @Value("\${roomPanel.height}") private val height: Int
) : PanelEventHandler<Graphics> {

    override fun handle(event: Graphics) {
        event.color = Color.GREEN
        event.fillRect(0, 0, width, height)
    }
}
