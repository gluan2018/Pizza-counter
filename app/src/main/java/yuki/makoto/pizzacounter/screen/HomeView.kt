package yuki.makoto.pizzacounter.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import yuki.makoto.pizzacounter.theme.PizzaCounterTheme
import yuki.makoto.pizzacounter.util.ActionCounter

@Composable
fun HomeView(callback: ActionCounter) {
    PizzaCounterTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            verticalArrangement = Arrangement.Center
        ) {
            //Counter(callback = callback)
        }
    }
}