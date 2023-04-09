package yuki.makoto.weareable.pizzacounter.presentation.util

fun interface Action<T> {
    fun action(data: T)
}