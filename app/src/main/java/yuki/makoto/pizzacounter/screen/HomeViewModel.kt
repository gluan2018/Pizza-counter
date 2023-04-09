package yuki.makoto.pizzacounter.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import yuki.makoto.connection.interfaces.WearableClient
import yuki.makoto.connection.interfaces.WearableListener
import yuki.makoto.pizzacounter.util.Action
import yuki.makoto.pizzacounter.util.ActionCounter
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val logger = Logger.t(this::class.simpleName)

    private val listenerWearable: WearableListener = WearableListener { byteArray ->
        logger.i("Wearable listener receive: ${byteArray.decodeToString()}")
        byteArray.decodeToString().toIntOrNull()?.apply(actionCounter::set)
    }

    private val listenerCounter: Action<Int> = Action { count ->
        viewModelScope.launch {
            logger.i("Wearable send: $count")
            wearableClient?.send(actionCounter.toString().toByteArray())
        }
    }

    @set:Inject
    var wearableClient: WearableClient? = null
        set(value) {
            field = value
            wearableClient?.addUpdateListener(listenerWearable)
        }

    val actionCounter: ActionCounter = ActionCounter(senderCallback = listenerCounter)

    override fun onCleared() {
        logger.d("Clear CounterViewModel")
        wearableClient?.removeUpdateListener(listenerWearable)
    }
}