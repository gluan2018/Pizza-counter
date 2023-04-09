package yuki.makoto.pizzacounter.view

import android.graphics.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.core.graphics.applyCanvas
import yuki.makoto.pizzacounter.R
import kotlin.math.min

@Composable
fun PizzaView(count: Int) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            bitmap = createImage(
                count = count,
                height = min(this.maxHeight.value, this.maxWidth.value).toInt()
            ).asImageBitmap(),
            contentDescription = "Pizza counter",
            modifier = Modifier
                .fillMaxSize(),
        )
    }
}

@Composable
private fun createImage(count: Int, height: Int): Bitmap {
    val resources = LocalContext.current.resources
    val imagePizza = remember {
        val img = ImageBitmap.imageResource(resources, R.drawable.rpizza)
            .asAndroidBitmap()
        Bitmap.createScaledBitmap(img, height / 2, height / 2, false)
    }

    val imageBitmap = remember(height) {
        Bitmap.createBitmap(height, height, Bitmap.Config.ARGB_8888)
    }

    val imageMatrix = remember("matrix") {
        Matrix()
    }

    imageBitmap.applyCanvas {
        drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
        for (position in 0..(count % 8)) {
            imageMatrix.reset()
            drawPizza(position, imagePizza, imageMatrix)
        }
    }

    return imageBitmap
}

private fun Canvas.drawPizza(position: Int, imagePizza: Bitmap, imageMatrix: Matrix) {
    imageMatrix.setTranslate(height / 4f, 0f)
    imageMatrix.postRotate(45f * position, height / 2f, height / 2f)
    drawBitmap(imagePizza, imageMatrix, null)
}