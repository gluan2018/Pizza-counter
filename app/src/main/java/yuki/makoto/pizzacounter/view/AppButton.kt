package yuki.makoto.pizzacounter.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(Dp(64f))
            .background(Color.Transparent),
        colors = ButtonDefaults
            .buttonColors(contentColor = Color.Transparent, containerColor = Color.Transparent)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = TextUnit(32f, TextUnitType.Sp)
        )
    }
}