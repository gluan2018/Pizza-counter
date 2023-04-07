package yuki.makoto.connection.interfaces

fun interface WearableListener {
    fun onMessage(byteArray: ByteArray)
}