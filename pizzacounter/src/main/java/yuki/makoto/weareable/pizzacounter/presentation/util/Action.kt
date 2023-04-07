package yuki.makoto.weareable.pizzacounter.presentation.util

interface ActionCounter {
    val current: Int

    val complete: Int

    fun plus(): Boolean

    fun minus(): Boolean

    fun setUpdateListener(callback: Action<Int>)
}

fun interface Action<T> {
    fun action(data: T)
}