package com.hussein.animationcompose.compsoe

import android.graphics.PointF
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.plus
import androidx.core.graphics.times
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.star
import androidx.graphics.shapes.toPath
import com.hussein.animationcompose.R
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
@Preview(showBackground = true)
fun PolygonShape(modifier: Modifier = Modifier) {
    //Hexagon with 6 vertices
    Box(
        modifier = Modifier
            .drawWithCache {
                val roundedPolygon = RoundedPolygon(
                    numVertices = 6,
                    radius = size.minDimension / 2,
                    centerX = size.width / 2,
                    centerY = size.height / 2
                )
                val roundedPolygonPath = roundedPolygon
                    .toPath()
                    .asComposePath()
                onDrawBehind {
                    drawPath(roundedPolygonPath, color = Color.Blue)
                }
            }
            .fillMaxSize()
    )
}

@Composable
@Preview(showBackground = true)
fun RoundedCorners(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .drawWithCache {
                val roundedPolygon = RoundedPolygon(
                    numVertices = 3,
                    radius = size.minDimension / 2,
                    centerX = size.width / 2,
                    centerY = size.height / 2,
                    rounding = CornerRounding(
                        size.minDimension / 10f,
                        smoothing = 0.1f
                    )
                )
                val roundedPolygonPath = roundedPolygon
                    .toPath()
                    .asComposePath()
                onDrawBehind {
                    drawPath(roundedPolygonPath, color = Color.Black)
                }
            }
            .size(100.dp)
    )
}

@Composable
@Preview(showBackground = true)
fun MorphShape(modifier: Modifier = Modifier) {
    /**  A Morph object is a new shape representing an animation between two polygonal shapes.
     * To morph between two shapes, create two RoundedPolygons and a Morph object that takes these two shapes. */
    Box(
        modifier = Modifier
            .drawWithCache {
                val triangle = RoundedPolygon(
                    numVertices = 3,
                    radius = size.minDimension / 2f,
                    centerX = size.width / 2f,
                    centerY = size.height / 2f,
                    rounding = CornerRounding(
                        size.minDimension / 10f,
                        smoothing = 0.1f
                    )
                )
                val square = RoundedPolygon(
                    numVertices = 4,
                    radius = size.minDimension / 2f,
                    centerX = size.width / 2f,
                    centerY = size.height / 2f
                )

                val morph = Morph(start = triangle, end = square)
                val morphPath = morph
                    .toPath(progress = 0.5f)
                    .asComposePath()

                onDrawBehind {
                    drawPath(morphPath, color = Color.Black)
                }
            }
            .fillMaxSize()
    )
}

@Composable
@Preview(showBackground = true)
fun AnimatedMorph(modifier: Modifier = Modifier) {
    val infiniteAnimation = rememberInfiniteTransition(label = "infinite animation")
    val morphProgress = infiniteAnimation.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "morph"
    )
    Box(
        modifier = Modifier
            .drawWithCache {
                val triangle = RoundedPolygon(
                    numVertices = 3,
                    radius = size.minDimension / 2f,
                    centerX = size.width / 2f,
                    centerY = size.height / 2f,
                    rounding = CornerRounding(
                        size.minDimension / 10f,
                        smoothing = 0.1f
                    )
                )
                val square = RoundedPolygon(
                    numVertices = 4,
                    radius = size.minDimension / 2f,
                    centerX = size.width / 2f,
                    centerY = size.height / 2f
                )

                val morph = Morph(start = triangle, end = square)
                val morphPath = morph
                    .toPath(progress = morphProgress.value)
                    .asComposePath()

                onDrawBehind {
                    drawPath(morphPath, color = Color.Black)
                }
            }
            .fillMaxSize()
    )
}

@Composable
@Preview(showBackground = true)
fun RoundedCornerPolygon(modifier: Modifier = Modifier) {
    val hexagon = remember {
        RoundedPolygon(
            6,
            rounding = CornerRounding(0.2f)
        )
    }
    val clip = remember(hexagon) {
        RoundedPolygonShape(polygon = hexagon)
    }
    Box(
        modifier = Modifier
            .clip(clip)
            .background(MaterialTheme.colorScheme.secondary)
            .size(200.dp)
    ) {
        Text(
            "Hello Compose",
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PolygonImage(modifier: Modifier = Modifier) {
    val hexagon = remember {
        RoundedPolygon(
            6,
            rounding = CornerRounding(0.2f)
        )
    }
    val clip = remember(hexagon) {
        RoundedPolygonShape(polygon = hexagon) // This is Custom on founded in RoundedPolygonShape.kt
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_1),
            contentDescription = "Dog",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .graphicsLayer {
                    this.shadowElevation = 6.dp.toPx()
                    this.shape = clip
                    this.clip = true
                    this.ambientShadowColor = Color.Black
                    this.spotShadowColor = Color.Black
                }
                .size(200.dp)

        )
    }
}

@Composable
@Preview(showBackground = true)
fun ClickableMorph(){
    val shapeA = remember {
        RoundedPolygon(
            6,
            rounding = CornerRounding(0.2f)
        )
    }
    val shapeB = remember {
        RoundedPolygon.star(
            6,
            rounding = CornerRounding(0.1f)
        )
    }
    val morph = remember {
        Morph(shapeA, shapeB)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val isPressed by interactionSource.collectIsPressedAsState()
    val animatedProgress = animateFloatAsState(
        targetValue = if (isPressed) 1f else 0f,
        label = "progress",
        animationSpec = spring(dampingRatio = 0.4f, stiffness = Spring.StiffnessMedium)
    )
    Box(
        modifier = Modifier
            .size(200.dp)
            .padding(8.dp)
            .clip(MorphPolygonShape(morph, animatedProgress.value))
            .background(Color(0xFF80DEEA))
            .size(200.dp)
            .clickable(interactionSource = interactionSource, indication = null) {
            }
    ) {
        Text("Hello", modifier = Modifier.align(Alignment.Center))
    }
}

@Preview
@Composable
fun RotatingScallopedProfilePic() {
    val shapeA = remember {
        RoundedPolygon(
            12,
            rounding = CornerRounding(0.2f)
        )
    }
    val shapeB = remember {
        RoundedPolygon.star(
            12,
            rounding = CornerRounding(0.2f)
        )
    }
    val morph = remember {
        Morph(shapeA, shapeB)
    }
    val infiniteTransition = rememberInfiniteTransition("infinite outline movement")
    val animatedProgress = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "animatedMorphProgress"
    )
    val animatedRotation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(6000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "animatedMorphProgress"
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_1),
            contentDescription = "Dog",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(
                    CustomRotatingMorphShape(
                        morph,
                        animatedProgress.value,
                        animatedRotation.value
                    )
                )
                .size(200.dp)
        )
    }
}


@Composable
@Preview(showBackground = true)
fun HeartShape(modifier: Modifier = Modifier) {

    val vertices = remember {
        val radius = 1f
        val radiusSides = 0.8f
        val innerRadius = .1f
        floatArrayOf(
            radialToCartesian(radiusSides, 0f.toRadians()).x,
            radialToCartesian(radiusSides, 0f.toRadians()).y,
            radialToCartesian(radius, 90f.toRadians()).x,
            radialToCartesian(radius, 90f.toRadians()).y,
            radialToCartesian(radiusSides, 180f.toRadians()).x,
            radialToCartesian(radiusSides, 180f.toRadians()).y,
            radialToCartesian(radius, 250f.toRadians()).x,
            radialToCartesian(radius, 250f.toRadians()).y,
            radialToCartesian(innerRadius, 270f.toRadians()).x,
            radialToCartesian(innerRadius, 270f.toRadians()).y,
            radialToCartesian(radius, 290f.toRadians()).x,
            radialToCartesian(radius, 290f.toRadians()).y,
        )
    }


    val rounding = remember {
        val roundingNormal = 0.6f
        val roundingNone = 0f
        listOf(
            CornerRounding(roundingNormal),
            CornerRounding(roundingNone),
            CornerRounding(roundingNormal),
            CornerRounding(roundingNormal),
            CornerRounding(roundingNone),
            CornerRounding(roundingNormal),
        )
    }

    val polygon = remember(vertices, rounding) {
        RoundedPolygon(
            vertices = vertices,
            perVertexRounding = rounding
        )
    }
    Box(
        modifier = Modifier
            .drawWithCache {
                val roundedPolygonPath = polygon.toPath().asComposePath()
                onDrawBehind {
                    scale(size.width * 0.5f, size.width * 0.5f) {
                        translate(size.width * 0.5f, size.height * 0.5f) {
                            drawPath(roundedPolygonPath, color = Color(0xFFF15087))
                        }
                    }
                }
            }
            .size(400.dp)
    )
}
internal fun Float.toRadians() = this * PI.toFloat() / 180f

internal val PointZero = PointF(0f, 0f)
internal fun radialToCartesian(
    radius: Float,
    angleRadians: Float,
    center: PointF = PointZero
) = directionVectorPointF(angleRadians) * radius + center

internal fun directionVectorPointF(angleRadians: Float) =
    PointF(cos(angleRadians), sin(angleRadians))