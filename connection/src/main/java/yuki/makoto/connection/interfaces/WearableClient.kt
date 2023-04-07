package yuki.makoto.connection.interfaces

interface WearableClient {
    suspend fun send(byteArray: ByteArray): Boolean
    fun addUpdateListener(listener: WearableListener)
    fun removeUpdateListener(listener: WearableListener)
}