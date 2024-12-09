package com.hussein.animationcompose.compsoe

import android.graphics.RuntimeShader
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hussein.animationcompose.R
import org.intellij.lang.annotations.Language

@Composable
@Preview(showBackground = true)
fun BrushHorizontalGradientCanvas(modifier: Modifier = Modifier) {
    val brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue))
    Canvas(
        modifier = Modifier.size(200.dp),
        onDraw = {
            drawCircle(brush)
        }
    )
}
@Composable
@Preview(showBackground = true)
fun BrushVerticalCanvas(modifier: Modifier = Modifier) {
    val brush = Brush.verticalGradient(listOf(Color.Red, Color.Blue))
    Canvas(
        modifier = Modifier.size(200.dp),
        onDraw = {
            drawCircle(brush)
        }
    )
}

@Composable
@Preview(showBackground = true)
fun BrushLinearCanvas(modifier: Modifier = Modifier) {
    val brush = Brush.linearGradient(listOf(Color.Red, Color.Blue))
    Canvas(
        modifier = Modifier.size(200.dp),
        onDraw = {
            drawCircle(brush)
        }
    )
}

@Composable
@Preview(showBackground = true)
fun BrushRadialCanvas(modifier: Modifier = Modifier) {
    val brush = Brush.radialGradient(listOf(Color.Red, Color.Blue))
    Canvas(
        modifier = Modifier.size(200.dp),
        onDraw = {
            drawCircle(brush)
        }
    )
}

@Composable
@Preview(showBackground = true)
fun BrushSweepCanvas(modifier: Modifier = Modifier) {
    val brush = Brush.sweepGradient(listOf(Color.Red, Color.Blue))
    Canvas(
        modifier = Modifier.size(200.dp),
        onDraw = {
            drawCircle(brush)
        }
    )
}

@Composable
@Preview(showBackground = true)
fun BrushRepeatHorizontalCanvas(modifier: Modifier = Modifier) {
    //The following code will repeat the gradient pattern 4 times,
    // since the endX is set to 50.dp and the size is set to 200.dp:
    val listColors = listOf(Color.Yellow, Color.Red, Color.Blue)
    val tileSize = with(LocalDensity.current) {
        50.dp.toPx()
    }
    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(
                Brush.horizontalGradient(
                    listColors,
                    endX = tileSize,
                    tileMode = TileMode.Repeated // Repeat here
                )
            )
    )
}

@Composable
@Preview(showBackground = true)
fun ChangeBrushSize(modifier: Modifier = Modifier) {
    val listColors = listOf(Color.Yellow, Color.Red, Color.Blue)
    val customBrush = remember {
        object : ShaderBrush() {
            override fun createShader(size: Size): Shader {
                return LinearGradientShader(
                    colors = listColors,
                    from = Offset.Zero,
                    to = Offset(size.width / 4f, 0f),
                    tileMode = TileMode.Mirror
                )
            }
        }
    }
    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(customBrush)
    )
}

@Composable
@Preview(showBackground = true)
fun RadialGradient(modifier: Modifier = Modifier) {
  Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    listOf(Color(0xFF2be4dc), Color(0xFF243484))
                )
            )
    )
}

@Composable
@Preview(showBackground = true)
fun LargeRadialGradient(modifier: Modifier = Modifier) {
    //Large Radial Gradient
    val largeRadialGradient = object : ShaderBrush() {
        override fun createShader(size: Size): Shader {
            val biggerDimension = maxOf(size.height, size.width)
            return RadialGradientShader(
                colors = listOf(Color(0xFF2be4dc), Color(0xFF243484)),
                center = size.center,
                radius = biggerDimension / 2f,
                colorStops = listOf(0f, 0.95f)
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(largeRadialGradient)
    )
}

@Composable
@Preview(showBackground = true , device = "spec:width=300dp,height=500dp")
fun ImageAsBrush(modifier: Modifier = Modifier) {
    val imageBrush =
        ShaderBrush(ImageShader(ImageBitmap.imageResource(id = R.drawable.img_1)))

// Use ImageShader Brush with background
    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(imageBrush)
    )

// Use ImageShader Brush with TextStyle
    Text(
        text = "Hello Android!",
        style = TextStyle(
            brush = imageBrush,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 36.sp
        )
    )

// Use ImageShader Brush with DrawScope#drawCircle()
    Canvas(onDraw = {
        drawCircle(imageBrush)
    }, modifier = Modifier.size(200.dp))
}

val Coral = Color(0xFFF3A397)
val LightYellow = Color(0xFFF8EE94)


@Language("AGSL")
val CUSTOM_SHADER = """
    uniform float2 resolution;
    layout(color) uniform half4 color;
    layout(color) uniform half4 color2;

    half4 main(in float2 fragCoord) {
        float2 uv = fragCoord/resolution.xy;

        float mixValue = distance(uv, vec2(0, 1));
        return mix(color, color2, mixValue);
    }
""".trimIndent()
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
@Preview
fun ShaderBrushExample() {
    Box(
        modifier = Modifier
            .drawWithCache {
                val shader = RuntimeShader(CUSTOM_SHADER)
                val shaderBrush = ShaderBrush(shader)
                shader.setFloatUniform("resolution", size.width, size.height)
                onDrawBehind {
                    shader.setColorUniform(
                        "color",
                        android.graphics.Color.valueOf(
                            LightYellow.red, LightYellow.green,
                            LightYellow
                                .blue,
                            LightYellow.alpha
                        )
                    )
                    shader.setColorUniform(
                        "color2",
                        android.graphics.Color.valueOf(
                            Coral.red,
                            Coral.green,
                            Coral.blue,
                            Coral.alpha
                        )
                    )
                    drawRect(shaderBrush)
                }
            }
            .fillMaxWidth()
            .height(200.dp)
    )
}