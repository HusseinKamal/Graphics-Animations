package com.hussein.animationcompose.compsoe

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.hussein.animationcompose.R
import kotlin.io.path.Path

@Composable
fun ImageContainer(modifier: Modifier = Modifier) {
    //ContentScale.None: Don't apply any scaling to the source.
    // If the content is smaller than destination bounds, it won't be scaled up to fit the area.
    val imageModifier = Modifier
        .size(150.dp)
        .border(BorderStroke(1.dp, Color.Black))
        .background(Color.Yellow)
    Image(
        painter = painterResource(id = R.drawable.img),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Fit,
        modifier = imageModifier
    )

}

@Composable
fun RoundedImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.img_1),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
    )
}

@Composable
fun RoundedCornerImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.img_1),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
fun AddOvalImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.img_1),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(SquashedOval())
    )
}

@Composable
fun BorderedImage(modifier: Modifier = Modifier) {
    val borderWidth = 4.dp
    Image(
        painter = painterResource(id = R.drawable.img_1),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .border(
                BorderStroke(borderWidth, Color.Yellow),
                CircleShape
            )
            .padding(borderWidth)
            .clip(CircleShape)
    )
}

@Composable
fun ColoredBorderImage(modifier: Modifier = Modifier) {
    val colorsBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFF9575CD),
                Color(0xFFBA68C8),
                Color(0xFFE57373),
                Color(0xFFFFB74D),
                Color(0xFFFFF176),
                Color(0xFFAED581),
                Color(0xFF4DD0E1),
                Color(0xFF9575CD)
            )
        )
    }
    val borderWidth = 4.dp
    Image(
        painter = painterResource(id = R.drawable.img_1),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .border(
                BorderStroke(borderWidth, colorsBrush),
                CircleShape
            )
            .padding(borderWidth)
            .clip(CircleShape)
    )

}

@Composable
fun AspectRatioImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.img_1),
        contentDescription = stringResource(id = R.string.dog_content_description),
        modifier = Modifier.aspectRatio(16f / 9f)
    )
}

@Composable
fun TintImage(modifier: Modifier = Modifier) {
    //Vector Image
    /*Image(
        painter = painterResource(id = R.drawable.img),
        contentDescription = stringResource(id = R.string.dog_content_description),
        colorFilter = ColorFilter.tint(Color.Yellow)
    )*/
    //resource image
    Image(
        painter = painterResource(id = R.drawable.img),
        contentDescription = stringResource(id = R.string.dog_content_description),
        colorFilter = ColorFilter.tint(Color.Green, blendMode = BlendMode.Darken)
    )
}

@Composable
fun BlackWhiteImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.img_1),
        contentDescription = stringResource(id = R.string.dog_content_description),
        colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
    )
}

@Composable
fun ContrastBrightnessImage(modifier: Modifier = Modifier) {
    val contrast = 2f // 0f..10f (1 should be default)
    val brightness = -180f // -255f..255f (0 should be default)
    val colorMatrix = floatArrayOf(
        contrast, 0f, 0f, 0f, brightness,
        0f, contrast, 0f, 0f, brightness,
        0f, 0f, contrast, 0f, brightness,
        0f, 0f, 0f, 1f, 0f
    )
    Image(
        painter = painterResource(id = R.drawable.img_1),
        contentDescription = stringResource(id = R.string.dog_content_description),
        colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix))
    )
}

@Composable
fun InvertedImage(modifier: Modifier = Modifier) {
    val colorMatrix = floatArrayOf(
        -1f, 0f, 0f, 0f, 255f,
        0f, -1f, 0f, 0f, 255f,
        0f, 0f, -1f, 0f, 255f,
        0f, 0f, 0f, 1f, 0f
    )
    Image(
        painter = painterResource(id = R.drawable.img_1),
        contentDescription = stringResource(id = R.string.dog_content_description),
        colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix))
    )
}

@Composable
fun BlurImage(modifier: Modifier = Modifier) {
    //BlurEffect applied to image
    /*Image(
        painter = painterResource(id = R.drawable.img_1),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .blur(
                radiusX = 10.dp,
                radiusY = 10.dp,
                edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
            )
    )*/
    //BlurEdgeTreatment.Unbounded
    //For example, if we set the BlurredEdgeTreatment to Unbounded on the image,
    // the edges of the image appear blurred instead of sharp:
    Image(
        painter = painterResource(id = R.drawable.img_1),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .blur(
                radiusX = 10.dp,
                radiusY = 10.dp,
                edgeTreatment = BlurredEdgeTreatment.Unbounded
            )
            .clip(RoundedCornerShape(8.dp))
    )
}

@Composable
fun CustomizedOverlayImages(modifier: Modifier = Modifier) {
    //Mix 2 images
    val rainbowImage = ImageBitmap.imageResource(id = R.drawable.img)
    val dogImage = ImageBitmap.imageResource(id = R.drawable.img_1)
    val customPainter = remember {
        OverlayImagePainter(dogImage, rainbowImage)
    }
    Image(
        painter = customPainter,
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier.wrapContentSize()
    )
}