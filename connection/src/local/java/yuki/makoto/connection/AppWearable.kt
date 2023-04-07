package yuki.makoto.connection

import yuki.makoto.connection.interfaces.WearableClient
import yuki.makoto.connection.interfaces.WearableListener

class AppWearable : WearableClient {
    private var listeners: MutableList<WearableListener> = mutableListOf()

    override suspend fun send(byteArray: ByteArray): Boolean {
        if (byteArray.isNotEmpty())
            listeners.iterator().forEachRemaining { action -> action.onMessage(byteArray) }
        return true
    }

    override fun addUpdateListener(listener: WearableListener) {
        listeners.add(listener)
    }

    override fun removeUpdateListener(listener: WearableListener) {
        listeners.remove(listener)
    }
}