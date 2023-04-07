package yuki.makoto.pizzacounter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.isActive
import yuki.makoto.pizzacounter.ui.theme.PizzaCounterTheme

var counter = 0

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ViewModel", "Active: ${viewModel.viewModelScope.isActive}")
        setContent {
            PizzaCounterTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Counter {
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun Counter(callback: () -> Unit) {
    Column {
        Text(text = "$counter")
        Button(onClick = {
            counter++
            callback()
        }) {
            Text(text = "Add")
        }
        Button(onClick = {
            counter--
            callback()
        }) {
            Text(text = "Remove")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PizzaCounterTheme {
        Greeting("Android")
    }
}