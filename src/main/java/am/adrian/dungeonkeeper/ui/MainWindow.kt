package am.adrian.dungeonkeeper.ui

import am.adrian.dungeonkeeper.game.GameStateService
import am.adrian.dungeonkeeper.ui.handler.gamepanel.GamePanelKeyEventHandler
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.awt.Color
import java.awt.Container
import java.awt.Cursor
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.event.KeyEvent
import javax.swing.JFrame


@Component
class MainWindow(
    private val gameMapPanel: GameMapPanel,
    private val creaturesPanel: CreaturesPanel,
    private val stateService: GameStateService,
    private val keyEventHandler: GamePanelKeyEventHandler,
    @Value("\${window.size.auto}") autoSize: Boolean,
    @Value("\${window.width}") width: Int,
    @Value("\${window.height}") height: Int
) : JFrame() {

    init {
        isUndecorated = true
        defaultCloseOperation = EXIT_ON_CLOSE
        if (autoSize) {
            size = toolkit.screenSize
        } else {
            setSize(width, height)
        }
        cursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR)
        background = Color.BLACK

        addContentsToPane(contentPane)

        isVisible = true
    }

    companion object : Logging

    @Scheduled(fixedRate = 1L)
    fun update() {
        if (!stateService.isStarted()) {
            repaint()
        }
    }

    override fun processKeyEvent(e: KeyEvent?) = keyEventHandler.handle(e!!)

    private fun addContentsToPane(pane: Container) {
        pane.layout = GridBagLayout()

        val gamePanelConstraints = GridBagConstraints()
        gamePanelConstraints.fill = GridBagConstraints.BOTH
        gamePanelConstraints.gridx = 0
        gamePanelConstraints.gridy = 0
        gamePanelConstraints.weightx = 1.0
        gamePanelConstraints.weighty = 1.0
        pane.add(gameMapPanel, gamePanelConstraints)

        val creaturesPanelConstraints = GridBagConstraints()
        creaturesPanelConstraints.fill = GridBagConstraints.BOTH
        creaturesPanelConstraints.gridx = 0
        creaturesPanelConstraints.gridy = 1
        creaturesPanelConstraints.weightx = 1.0
        creaturesPanelConstraints.weighty = 0.2

        pane.add(creaturesPanel, creaturesPanelConstraints)
    }
}
