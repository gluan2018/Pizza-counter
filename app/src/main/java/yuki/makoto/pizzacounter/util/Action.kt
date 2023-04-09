package yuki.makoto.pizzacounter.util

fun interface Action<T> {
    fun action(data: T)
}