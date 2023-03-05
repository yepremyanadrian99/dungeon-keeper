package am.adrian.dungeonkeeper.ui.handler

interface UIEventHandler<E> {

    fun handle(event: E)
}
