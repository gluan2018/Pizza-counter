package yuki.makoto.weareable.pizzacounter.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.wear.compose.material.Text
import yuki.makoto.weareable.pizzacounter.R
import yuki.makoto.weareable.pizzacounter.presentation.theme.BlackTransparent
import yuki.makoto.weareable.pizzacounter.presentation.theme.PizzaCounterTheme
import yuki.makoto.weareable.pizzacounter.presentation.util.ActionCounter
import yuki.makoto.weareable.pizzacounter.presentation.view.AppButton
import yuki.makoto.weareable.pizzacounter.presentation.view.PizzaView

@Composable
fun CounterView(callback: ActionCounter) {
    PizzaCounterTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
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
                )
                Text(
                    text = stringResource(id = R.string.count_pizza_complete, callback.complete),
                    fontSize = TextUnit(16f, TextUnitType.Sp),
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
                    .background(BlackTransparent)
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