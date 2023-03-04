package am.adrian.dungeonkeeper.common.handler

interface GameEventHandler<E> {

    fun handle(event: E)
}
