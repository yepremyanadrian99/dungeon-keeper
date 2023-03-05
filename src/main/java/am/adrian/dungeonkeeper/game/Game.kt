package am.adrian.dungeonkeeper.game

import am.adrian.dungeonkeeper.game.handler.GameGraphicsHandler
import am.adrian.dungeonkeeper.game.handler.GameKeyEventHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.awt.Color
import java.awt.Cursor
import java.awt.Graphics
import java.awt.event.KeyEvent
import javax.swing.JFrame

@Component
class Game(
    private val stateService: GameStateService,
    private val graphicsHandler: GameGraphicsHandler,
    private val keyEventHandler: GameKeyEventHandler,
    @Value("\${window.width}") width: Int,
    @Value("\${window.height}") height: Int
) : JFrame() {

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(width, height)
        isVisible = true
        cursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR)
        background = Color.BLACK
    }

    @Scheduled(fixedRate = 1L)
    fun update() {
        if (!stateService.isStarted()) {
            repaint()
        }
    }

    override fun processKeyEvent(e: KeyEvent?) = keyEventHandler.handle(e!!)

    override fun paint(g: Graphics?) = graphicsHandler.handle(g!!)
}
