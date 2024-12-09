package com.hussein.animationcompose.compsoe

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.hussein.animationcompose.R
import kotlinx.coroutines.launch

@Composable
fun CanvasCompose(modifier: Modifier = Modifier) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasQuadrantSize = size / 2F
        drawRect(
            color = Color.Magenta,
            size = canvasQuadrantSize
        )
    }
}

@Composable
fun LineCompose(modifier: Modifier = Modifier) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawLine(
            start = Offset(x = canvasWidth, y = 0f),
            end = Offset(x = 0f, y = canvasHeight),
            color = Color.Blue
        )
    }

}

@Composable
fun CircleCompose(modifier: Modifier = Modifier) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        scale(scaleX = 10f, scaleY = 15f) {
            drawCircle(Color.Blue, radius = 20.dp.toPx())
        }
    }
}

@Composable
fun TranslatedCircle(modifier: Modifier = Modifier) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        translate(left = 100f, top = -300f) {
            drawCircle(Color.Blue, radius = 200.dp.toPx())
        }
    }
}

@Composable
fun RotatedCanvas(modifier: Modifier = Modifier) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        rotate(degrees = 45F) {
            drawRect(
                color = Color.Gray,
                topLeft = Offset(x = size.width / 3F, y = size.height / 3F),
                size = size / 3F
            )
        }
    }
}

@Composable
fun InsetCanvas(modifier: Modifier = Modifier) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasQuadrantSize = size / 2F
        inset(horizontal = 50f, vertical = 30f) {
            drawRect(color = Color.Green, size = canvasQuadrantSize)
        }
    }
}

@Composable
fun TransFormedRotatedCanvas(modifier: Modifier = Modifier) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        withTransform({
            translate(left = size.width / 5F)
            rotate(degrees = 45F)
        }) {
            drawRect(
                color = Color.Gray,
                topLeft = Offset(x = size.width / 3F, y = size.height / 3F),
                size = size / 3F
            )
        }
    }
}

@Composable
fun DrawText(modifier: Modifier=Modifier) {
    val textMeasurer = rememberTextMeasurer()

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawText(textMeasurer, "Hello")
    }
}

@Composable
fun DrawBackgroundTextMeasured(modifier: Modifier = Modifier){
    val textMeasurer = rememberTextMeasurer()

    Spacer(
        modifier = Modifier
            .drawWithCache {
                val measuredText =
                    textMeasurer.measure(
                        AnnotatedString("Hussein Kamal Thabet Amin"),
                        constraints = Constraints.fixedWidth((size.width * 2f / 3f).toInt()),
                        style = TextStyle(fontSize = 18.sp)
                    )

                onDrawBehind {
                    drawRect(Color.Magenta, size = measuredText.size.toSize())
                    drawText(measuredText)
                }
            }
            .fillMaxSize()
    )
}

@Composable
fun DrawBackgroundTextOverFlow(modifier: Modifier = Modifier) {
    val textMeasurer = rememberTextMeasurer()

    Spacer(
        modifier = Modifier
            .drawWithCache {
                val measuredText =
                    textMeasurer.measure(
                        AnnotatedString("Hussein Kamal Thabet Amin"),
                        constraints = Constraints.fixed(
                            width = (size.width / 3f).toInt(),
                            height = (size.height / 3f).toInt()
                        ),
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(fontSize = 18.sp)
                    )

                onDrawBehind {
                    drawRect(Color.Magenta, size = measuredText.size.toSize())
                    drawText(measuredText)
                }
            }
            .fillMaxSize()
    )
}

@Composable
fun DrawImage(modifier: Modifier = Modifier) {
    val dogImage = ImageBitmap.imageResource(id = R.drawable.img_1)

    Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
        drawImage(dogImage)
    })
}

@Composable
fun DrawPath(modifier: Modifier = Modifier) {
    Spacer(
        modifier = Modifier
            .drawWithCache {
                val path = Path()
                path.moveTo(0f, 0f)
                path.lineTo(size.width / 2f, size.height / 2f)
                path.lineTo(size.width, 0f)
                path.close()
                onDrawBehind {
                    drawPath(path, Color.Magenta, style = Stroke(width = 10f))
                }
            }
            .fillMaxSize()
    )
}
@Composable
fun DrawInvertedPath(modifier: Modifier = Modifier) {
    Spacer(
        modifier = Modifier
            .drawWithCache {
                val path = Path()
                path.moveTo(0f, size.height) // Start at bottom-left
                path.lineTo(size.width / 2f, size.height / 2f) // Go to the middle top
                path.lineTo(size.width, size.height) // Go to bottom-right
                path.close()
                onDrawBehind {
                    drawPath(path, Color.Magenta, style = Stroke(width = 10f))
                }
            }
            .fillMaxSize()
    )
}


@Composable
fun DrawColoredShape(modifier: Modifier = Modifier) {
    val drawable = ShapeDrawable(OvalShape())
    Spacer(
        modifier = Modifier
            .drawWithContent {
                drawIntoCanvas { canvas ->
                    drawable.setBounds(0, 0, size.width.toInt(), size.height.toInt())
                    drawable.draw(canvas.nativeCanvas)
                }
            }
            .fillMaxSize()
    )
}

@Composable
fun DrawChooseContent(modifier: Modifier = Modifier) {
    var pointerOffset by remember {
        mutableStateOf(Offset(0f, 0f))
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput("dragging") {
                detectDragGestures { change, dragAmount ->
                    pointerOffset += dragAmount
                }
            }
            .onSizeChanged {
                pointerOffset = Offset(it.width / 2f, it.height / 2f)
            }
            .drawWithContent {
                drawContent()
                // draws a fully black area with a small keyhole at pointerOffset that’ll show part of the UI.
                drawRect(
                    Brush.radialGradient(
                        listOf(Color.Transparent, Color.Black),
                        center = pointerOffset,
                        radius = 100.dp.toPx(),
                    )
                )
            }
    ) {
        // Your composables here
        Text(
            "Hello Compose!",
            modifier = Modifier
                .drawBehind {
                    drawRoundRect(
                        Color(0xFFBBAAEE),
                        cornerRadius = CornerRadius(10.dp.toPx())
                    )
                }
                .padding(4.dp)
        )
    }
    
}

@Composable
fun DrawTextWithGradient(modifier: Modifier = Modifier) {
    Text(
        "Hello Compose!",
        modifier = Modifier
            .drawWithCache {
                val brush = Brush.linearGradient(
                    listOf(
                        Color(0xFF9E82F0),
                        Color(0xFF42A5F5)
                    )
                )
                onDrawBehind {
                    drawRoundRect(
                        brush,
                        cornerRadius = CornerRadius(10.dp.toPx())
                    )
                }
            }
    )
}


@Composable
fun DrawImageScaleIncrease(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.img_1),
        contentDescription = "Sunset",
        modifier = Modifier
            .graphicsLayer {
                this.scaleX = 1.2f
                this.scaleY = 0.8f
            }
    )
}

@Composable
fun DrawImageTranslate(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.img_1),
        contentDescription = "Sunset",
        modifier = Modifier
            .graphicsLayer {
                this.translationX = 100.dp.toPx()
                this.translationY = 10.dp.toPx()
            }
    )
}

@Composable
fun DrawImageWithRotation(modifier: Modifier = Modifier) {
    /*Image(
        painter = painterResource(id = R.drawable.img_1),
        contentDescription = "Sunset",
        modifier = Modifier
            .graphicsLayer {
                this.rotationX = 90f
                this.rotationY = 275f
                this.rotationZ = 180f
            }
    )*/

    Image(
        painter = painterResource(id = R.drawable.img_1),
        contentDescription = "Sunset",
        modifier = Modifier
            .graphicsLayer {
                this.transformOrigin = TransformOrigin(0f, 0f)
                this.rotationX = 90f
                this.rotationY = 275f
                this.rotationZ = 180f
            }
    )
}

@Composable
fun ClipShape(modifier: Modifier = Modifier) {
    /*Column(modifier = Modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer {
                    clip = true
                    shape = CircleShape
                }
                .background(Color(0xFFF06292))
        ) {
            Text(
                "Hello Compose",
                style = TextStyle(color = Color.Black, fontSize = 46.sp),
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color(0xFF4DB6AC))
        )
    }*/

    Column(modifier = Modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
                .clip(RectangleShape)
                .size(200.dp)
                .border(2.dp, Color.Black)
                .graphicsLayer {
                    clip = true
                    shape = CircleShape
                    translationY = 50.dp.toPx()
                }
                .background(Color(0xFFF06292))
        ) {
            Text(
                "Hello Compose",
                style = TextStyle(color = Color.Black, fontSize = 46.sp),
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(500.dp))
                .background(Color(0xFF4DB6AC))
        )
    }
}

@Composable
fun ImageAlpha(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.img),
        contentDescription = "clock",
        modifier = Modifier
            .graphicsLayer {
                this.alpha = 0.5f
            }
    )
}

@Composable
fun DrawImageWithActiveCircle(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.img_1),
        contentDescription = "Dog",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(120.dp)
            .aspectRatio(1f)
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(0xFFC5E1A5),
                        Color(0xFF80DEEA)
                    )
                )
            )
            .padding(8.dp)
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
            }
            .drawWithCache {
                val path = Path()
                path.addOval(
                    Rect(
                        topLeft = Offset.Zero,
                        bottomRight = Offset(size.width, size.height)
                    )
                )
                onDrawWithContent {
                    clipPath(path) {
                        // this draws the actual image - if you don't call drawContent, it wont
                        // render anything
                        this@onDrawWithContent.drawContent()
                    }
                    val dotSize = size.width / 8f
                    // Clip a white border for the content
                    drawCircle(
                        Color.Black,
                        radius = dotSize,
                        center = Offset(
                            x = size.width - dotSize,
                            y = size.height - dotSize
                        ),
                        blendMode = BlendMode.Clear
                    )
                    // draw the red circle indication
                    drawCircle(
                        Color(0xFFEF5350), radius = dotSize * 0.8f,
                        center = Offset(
                            x = size.width - dotSize,
                            y = size.height - dotSize
                        )
                    )
                }
            }
    )
}

@Composable
fun CompositingStrategyExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        // Does not clip content even with a graphics layer usage here. By default, graphicsLayer
        // does not allocate + rasterize content into a separate layer but instead is used
        // for isolation. That is draw invalidations made outside of this graphicsLayer will not
        // re-record the drawing instructions in this composable as they have not changed
        Canvas(
            modifier = Modifier
                .graphicsLayer()
                .size(100.dp) // Note size of 100 dp here
                .border(2.dp, color = Color.Blue)
        ) {
            // ... and drawing a size of 200 dp here outside the bounds
            drawRect(color = Color.Magenta, size = Size(200.dp.toPx(), 200.dp.toPx()))
        }

        Spacer(modifier = Modifier.size(300.dp))

        /* Clips content as alpha usage here creates an offscreen buffer to rasterize content
        into first then draws to the original destination */
        Canvas(
            modifier = Modifier
                // force to an offscreen buffer
                .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
                .size(100.dp) // Note size of 100 dp here
                .border(2.dp, color = Color.Blue)
        ) {
            /* ... and drawing a size of 200 dp. However, because of the CompositingStrategy.Offscreen usage above, the
            content gets clipped */
            drawRect(color = Color.Red, size = Size(200.dp.toPx(), 200.dp.toPx()))
        }
    }
}

@Composable
fun CompositingStrategy_ModulateAlpha() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        // Base drawing, no alpha applied
        Canvas(
            modifier = Modifier.size(200.dp)
        ) {
            drawSquares()
        }

        Spacer(modifier = Modifier.size(36.dp))

        // Alpha 0.5f applied to whole composable
        Canvas(
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer {
                    alpha = 0.5f
                }
        ) {
            drawSquares()
        }
        Spacer(modifier = Modifier.size(36.dp))

        // 0.75f alpha applied to each draw call when using ModulateAlpha
        Canvas(
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer {
                    compositingStrategy = CompositingStrategy.ModulateAlpha
                    alpha = 0.75f
                }
        ) {
            drawSquares()
        }
    }
}

private fun DrawScope.drawSquares() {

    val size = Size(100.dp.toPx(), 100.dp.toPx())
    drawRect(color = Red, size = size)
    drawRect(
        color = Purple, size = size,
        topLeft = Offset(size.width / 4f, size.height / 4f)
    )
    drawRect(
        color = Yellow, size = size,
        topLeft = Offset(size.width / 4f * 2f, size.height / 4f * 2f)
    )
}

val Purple = Color(0xFF7E57C2)
val Yellow = Color(0xFFFFCA28)
val Red = Color(0xFFEF5350)


@Composable
fun DrawClickableLayer(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val graphicsLayer = rememberGraphicsLayer()
    Box(
        modifier = Modifier
            .drawWithContent {
                // call record to capture the content in the graphics layer
                graphicsLayer.record {
                    // draw the contents of the composable into the graphics layer
                    this@drawWithContent.drawContent()
                }
                // draw the graphics layer on the visible canvas
                drawLayer(graphicsLayer)
            }
            .clickable {
                coroutineScope.launch {
                    val bitmap = graphicsLayer.toImageBitmap()
                    // do something with the newly acquired bitmap
                }
            }
            .background(Color.White)
    ) {
        Text("Hello Android", fontSize = 26.sp)
    }
}

//Flip text ------------------
fun Modifier.flipped() = this.then(FlippedModifier())
@Composable
fun FlipText(modifier: Modifier = Modifier) {
    Text(
        "Hello Compose!",
        modifier = Modifier
            .flipped()
    )
}