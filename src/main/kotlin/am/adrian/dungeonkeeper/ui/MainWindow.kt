package am.adrian.dungeonkeeper.ui

import am.adrian.dungeonkeeper.game.GameStateService
import am.adrian.dungeonkeeper.ui.creature.CreaturePanel
import am.adrian.dungeonkeeper.ui.gamemap.GameMapPanel
import am.adrian.dungeonkeeper.ui.gamemap.handler.GameMapPanelKeyEventHandler
import am.adrian.dungeonkeeper.ui.room.RoomPanel
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.awt.Color
import java.awt.Container
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.event.KeyEvent
import javax.swing.JFrame


@Component
class MainWindow(
    private val gameMapPanel: GameMapPanel,
    private val creaturePanel: CreaturePanel,
    private val roomPanel: RoomPanel,
    private val stateService: GameStateService,
    private val keyEventHandler: GameMapPanelKeyEventHandler,
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
        gamePanelConstraints.gridwidth = 1
        gamePanelConstraints.gridheight = 1
        pane.add(gameMapPanel, gamePanelConstraints)

        val creaturePanelConstraints = GridBagConstraints()
        creaturePanelConstraints.fill = GridBagConstraints.BOTH
        creaturePanelConstraints.gridx = 0
        creaturePanelConstraints.gridy = 1
        creaturePanelConstraints.weightx = 1.0
        creaturePanelConstraints.weighty = 0.32
        creaturePanelConstraints.gridwidth = 2
        creaturePanelConstraints.gridheight = 1
        pane.add(creaturePanel, creaturePanelConstraints)

        val roomPanelConstraints = GridBagConstraints()
        roomPanelConstraints.fill = GridBagConstraints.BOTH
        roomPanelConstraints.gridx = 1
        roomPanelConstraints.gridy = 0
        roomPanelConstraints.weightx = 0.32
        roomPanelConstraints.weighty = 1.0
        roomPanelConstraints.gridwidth = 1
        roomPanelConstraints.gridheight = 1
        pane.add(roomPanel, roomPanelConstraints)
    }
}
