package yuki.makoto.pizzacounter.util

class ActionCounter(
    private val senderCallback: Action<Int>
) {
    private var counter: Int = 0
    private var onSetCallbackListener: Action<Int>? = null

    val current: Int
        get() = counter

    val complete: Int
        get() = counter / 8

    fun plus(): Int {
        if (counter < 99)
            counter++
        senderCallback.action(data = counter)
        return counter
    }

    fun minus(): Int {
        if (counter > 0)
            counter--
        senderCallback.action(data = counter)
        return counter
    }

    fun set(value: Int) {
        counter = value
        onSetCallbackListener?.action(value)
    }

    fun setOnSetCounterListener(listener: Action<Int>?) {
        onSetCallbackListener = listener
    }

    override fun toString(): String {
        return counter.toString()
    }
}