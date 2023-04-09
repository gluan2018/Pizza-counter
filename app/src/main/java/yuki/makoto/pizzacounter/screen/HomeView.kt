package yuki.makoto.pizzacounter.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import yuki.makoto.pizzacounter.R
import yuki.makoto.pizzacounter.theme.PizzaCounterTheme
import yuki.makoto.pizzacounter.util.ActionCounter
import yuki.makoto.pizzacounter.view.AppButton
import yuki.makoto.pizzacounter.view.PizzaView

@Composable
fun HomeView(callback: ActionCounter) {
    PizzaCounterTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF365ED6)),
            verticalArrangement = Arrangement.Center
        ) {
            Counter(callback = callback)
        }
    }
}

@Composable
private fun Counter(callback: ActionCounter) {
    var counter by remember {
        mutableStateOf(callback.current - 1)
    }

    callback.setOnSetCounterListener { newValue ->
        counter = newValue - 1
    }

    Box(
        Modifier.fillMaxSize()
    ) {
        PizzaView(count = counter)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "%02d".format(counter + 1),
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(24f, TextUnitType.Sp),
                    color = Color.White
                )
                Text(
                    text = stringResource(id = R.string.count_pizza_complete, callback.complete),
                    fontSize = TextUnit(16f, TextUnitType.Sp),
                    color = Color.White
                )
            }
        }
        BoxWithConstraints(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dp(64f))
                    .background(Color.Transparent)
            ) {
                AppButton(text = "+") {
                    counter = callback.plus() - 1
                }
                Spacer(modifier = Modifier.width(Dp(16f)))
                AppButton(text = "-") {
                    counter = callback.minus() - 1
                }
            }
        }
    }

}