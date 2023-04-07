package yuki.makoto.weareable.pizzacounter.presentation.screen

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import yuki.makoto.connection.interfaces.WearableClient
import yuki.makoto.connection.interfaces.WearableListener
import yuki.makoto.weareable.pizzacounter.presentation.util.Action
import yuki.makoto.weareable.pizzacounter.presentation.util.ActionCounter
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor() : ViewModel(), ActionCounter {
    private var counter: Int = 0
    private var listenerAction: Action<Int>? = null

    private val listenerWearable: WearableListener = WearableListener { bundle ->
        counter = bundle.getInt("counter")
    }

    @set:Inject
    var service: WearableClient? = null
        set(value) {
            field = value
            service?.addUpdateListener(listenerWearable)
        }

    override val current: Int
        get() = counter

    override val complete: Int
        get() = counter / 8

    override fun plus(): Boolean {
        if (counter >= 99)
            return false
        counter++
        viewModelScope.launch { service?.send(createRequest()) }

        return true
    }

    override fun minus(): Boolean {
        if (counter <= 0)
            return false
        counter--
        viewModelScope.launch { service?.send(createRequest()) }

        return true
    }

    override fun setUpdateListener(callback: Action<Int>) {
        listenerAction = callback
    }

    override fun onCleared() {
        service?.removeUpdateListener(listenerWearable)
    }

    private fun createRequest() = Bundle().apply { putInt("counter", counter) }
}