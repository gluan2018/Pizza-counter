package yuki.makoto.weareable.pizzacounter.presentation.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import yuki.makoto.weareable.pizzacounter.presentation.util.Action
import yuki.makoto.weareable.pizzacounter.presentation.util.ActionCounter

@AndroidEntryPoint
class CounterActivity : ComponentActivity() {

    private val viewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterView(callback = viewModel)
        }
    }
}

@Preview(device = Devices.WEAR_OS_LARGE_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    CounterView(callback = object : ActionCounter {
        private var count = 0
        override val current: Int
            get() = count
        override val complete: Int
            get() = current / 8
        override fun plus(): Boolean {
            count++
            return true
        }
        override fun minus(): Boolean {
            count--
            return true
        }
        override fun setUpdateListener(callback: Action<Int>) = Unit
    })
}