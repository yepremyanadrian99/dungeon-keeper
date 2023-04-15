package am.adrian.dungeonkeeper.ui

interface PanelEventHandler<E> {

    fun handle(event: E)
}
