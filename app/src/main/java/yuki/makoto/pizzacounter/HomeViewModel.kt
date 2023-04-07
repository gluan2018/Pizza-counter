package yuki.makoto.pizzacounter

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import yuki.makoto.connection.interfaces.WearableClient
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    @set:Inject
    var service: WearableClient? = null
        set(value) {
            field = value
            value?.addUpdateListener { bundle ->
                Log.i("WearableClient", "Counter: ${bundle.getString("counter")}")
            }
        }
}