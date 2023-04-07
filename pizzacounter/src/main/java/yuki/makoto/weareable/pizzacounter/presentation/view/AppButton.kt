package yuki.makoto.weareable.pizzacounter.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Text
import yuki.makoto.weareable.pizzacounter.presentation.theme.Gray
import yuki.makoto.weareable.pizzacounter.presentation.theme.White

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(Dp(32f))
            .background(Color.Transparent),
        colors = ButtonDefaults
            .buttonColors(backgroundColor = Color.Transparent)
    ) {
        Text(
            text = text,
            color = White,
        )
    }
}