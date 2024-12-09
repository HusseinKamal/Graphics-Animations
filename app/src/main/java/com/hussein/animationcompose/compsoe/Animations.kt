package com.hussein.animationcompose.compsoe

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.AnimationVector3D
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.splineBasedDecay
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil3.compose.AsyncImage
import com.hussein.animationcompose.R
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@Composable
fun AnimateFloatAsState(modifier: Modifier = Modifier) {
    var visible by remember {
        mutableStateOf(true)
    }
    val animatedAlpha by animateFloatAsState(
        targetValue = if (visible) 1.0f else 0f,
        label = "alpha"
    )
    Box(
        modifier = Modifier
            .size(200.dp)
            .graphicsLayer {
                alpha = animatedAlpha
            }
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Green)
            .clickable {
                visible = !visible
            }
    ) {
    }
}

@Composable
fun AnimationContentSize(modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .background(Color.Blue)
            .animateContentSize()
            .height(if (expanded) 400.dp else 200.dp)
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                expanded = !expanded
            }

    ) {
    }
}

@Composable
fun AnimatePosition(modifier: Modifier = Modifier) {
    var moved by remember { mutableStateOf(false) }
    val pxToMove = with(LocalDensity.current) {
        100.dp.toPx().roundToInt()
    }
    val offset by animateIntOffsetAsState(
        targetValue = if (moved) {
            IntOffset(pxToMove, pxToMove)
        } else {
            IntOffset.Zero
        },
        label = "offset"
    )

    Box(
        modifier = Modifier
            .offset {
                offset
            }
            .background(Color.Black)
            .size(100.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                moved = !moved
            }
    )
}

@Composable
fun AnimationPositionsRelative(modifier: Modifier = Modifier) {
    var toggled by remember {
        mutableStateOf(false)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .clickable(indication = null, interactionSource = interactionSource) {
                toggled = !toggled
            }
    ) {
        val offsetTarget = if (toggled) {
            IntOffset(150, 150)
        } else {
            IntOffset.Zero
        }
        val offset = animateIntOffsetAsState(
            targetValue = offsetTarget, label = "offset"
        )
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Blue)
        )
        Box(
            modifier = Modifier
                .layout { measurable, constraints ->
                    val offsetValue = if (isLookingAhead) offsetTarget else offset.value
                    val placeable = measurable.measure(constraints)
                    layout(placeable.width + offsetValue.x, placeable.height + offsetValue.y) {
                        placeable.placeRelative(offsetValue)
                    }
                }
                .size(100.dp)
                .background(Color.Green)
        )
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Magenta)
        )
    }
}

@Composable
fun AnimationPadding(modifier: Modifier = Modifier) {
    var toggled by remember {
        mutableStateOf(false)
    }
    val animatedPadding by animateDpAsState(
        if (toggled) {
            0.dp
        } else {
            20.dp
        },
        label = "padding"
    )
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .fillMaxSize()
            .padding(animatedPadding)
            .background(Color(0xff53D9A1))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                toggled = !toggled
            }
    )
}

@Composable
fun AnimationShadow(modifier: Modifier = Modifier) {
    val mutableInteractionSource = remember {
        MutableInteractionSource()
    }
    val pressed = mutableInteractionSource.collectIsPressedAsState()
    val elevation = animateDpAsState(
        targetValue = if (pressed.value) {
            32.dp
        } else {
            8.dp
        },
        label = "elevation"
    )
    Box(
        modifier = Modifier
            .size(100.dp)
            .graphicsLayer {
                this.shadowElevation = elevation.value.toPx()
            }
            .clickable(interactionSource = mutableInteractionSource, indication = null) {
            }
            .background(Color.Green)
    ) {
    }
}

@Composable
fun AnimationText(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 8f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "scale"
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Hello",
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    transformOrigin = TransformOrigin.Center
                }
                .align(Alignment.Center),
            // Text composable does not take TextMotion as a parameter.
            // Provide it via style argument but make sure that we are copying from current theme
            style = LocalTextStyle.current.copy(textMotion = TextMotion.Animated)
        )
    }
}

@Composable
fun AnimationNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = "landing",
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable("landing") {
            Button(modifier= Modifier
                .fillMaxSize(),
                onClick = {
                    val photoUrl =
                        "https://letsenhance.io/static/a31ab775f44858f1d1b80ee51738f4f3/11499/EnhanceAfter.jpg" // Example URL
                    // Encode the URL before navigating
                    val encodedUrl = URLEncoder.encode(photoUrl, StandardCharsets.UTF_8.toString())
                    navController.navigate("detail/$encodedUrl")
                }
            ){
                Text(text = "Details")
            }
        }
        composable(
            "detail/{photoUrl}",
            arguments = listOf(navArgument("photoUrl") { type = NavType.StringType }),
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) { backStackEntry ->
            val photoUrl = backStackEntry.arguments?.getString("photoUrl")
            if (photoUrl != null) {
                val encodedPhotoUrl = backStackEntry.arguments?.getString("photoUrl")
                AsyncImage(
                    model = encodedPhotoUrl,
                    contentDescription = "ImageDetails",
                )
            } else {
                // Handle the case where the photo URL is missing
                Text("Image URL not found")
            }
        }
    }
}

@Composable
fun AnimationVisibility(modifier: Modifier = Modifier) {
    var visible by remember { mutableStateOf(true) }
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically {
            // Slide in from 40 dp from the top.
            with(density) { -40.dp.roundToPx() }
        } + expandVertically(
            // Expand from the top.
            expandFrom = Alignment.Top
        ) + fadeIn(
            // Fade in with the initial alpha of 0.3f.
            initialAlpha = 0.3f
        ),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        Text(
            "Hello",
            Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}

@Composable
fun AnimationVisibilityFade(modifier: Modifier = Modifier) {
    var visible by remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        // Fade in/out the background and the foreground.
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        ) {
            Box(
                Modifier
                    .align(Alignment.Center)
                    .animateEnterExit(
                        // Slide in/out the inner box.
                        enter = slideInVertically(),
                        exit = slideOutVertically()
                    )
                    .sizeIn(minWidth = 256.dp, minHeight = 64.dp)
                    .background(Color.Red)
                    .clickable {
                        visible = !visible
                    }
            ) {
                // Content of the notification…
            }
        }
    }
}

@Composable
fun AnimationSlidingTopDownText(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }
    Button(onClick = { count++ }) {
        Text("Add")
    }
    AnimatedContent(
        targetState = count,
        transitionSpec = {
            // Compare the incoming number with the previous number.
            if (targetState > initialState) {
                // If the target number is larger, it slides up and fades in
                // while the initial (smaller) number slides up and fades out.
                slideInVertically { height -> height } + fadeIn() togetherWith
                        slideOutVertically { height -> -height } + fadeOut()
            } else {
                // If the target number is smaller, it slides down and fades in
                // while the initial number slides down and fades out.
                slideInVertically { height -> -height } + fadeIn() togetherWith
                        slideOutVertically { height -> height } + fadeOut()
            }.using(
                // Disable clipping since the faded slide-in/out should
                // be displayed out of bounds.
                SizeTransform(clip = false)
            )
        }, label = "animated content"
    ) { targetCount ->
        Text(text = "$targetCount")
    }
}

@Composable
fun AnimationSurfaceClick(modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    Surface(
        color = MaterialTheme.colorScheme.primary,
        onClick = { expanded = !expanded }
    ) {
        AnimatedContent(
            targetState = expanded,
            transitionSpec = {
                fadeIn(animationSpec = tween(150, 150)) togetherWith
                        fadeOut(animationSpec = tween(150)) using
                        SizeTransform { initialSize, targetSize ->
                            if (targetState) {
                                keyframes {
                                    // Expand horizontally first.
                                    IntSize(targetSize.width, initialSize.height) at 150
                                    durationMillis = 300
                                }
                            } else {
                                keyframes {
                                    // Shrink vertically first.
                                    IntSize(initialSize.width, targetSize.height) at 150
                                    durationMillis = 300
                                }
                            }
                        }
            }, label = "size transform"
        ) { targetExpanded ->
            if (targetExpanded) {
                Text(text = "Asser Hussein Kamal")
            } else {
                Image(imageVector = Icons.Default.Person, contentDescription = "Details")
            }
        }
    }
}

@Composable
fun AnimateFloatAsStateValue(modifier: Modifier = Modifier) {
    var enabled by remember { mutableStateOf(true) }

    val alpha: Float by animateFloatAsState(if (enabled) 1f else 0.5f, label = "alpha")
    Box(
        Modifier
            .fillMaxSize()
            .graphicsLayer(alpha = alpha)
            .background(Color.Red)
            .clickable {
                enabled = !enabled
            }
    )
}

@Composable
fun AnimationContentWithBorders(modifier: Modifier = Modifier) {
    var selected by remember { mutableStateOf(false) }
// Animates changes when `selected` is changed.
    val transition = updateTransition(selected, label = "selected state")
    val borderColor by transition.animateColor(label = "border color") { isSelected ->
        if (isSelected) Color.Magenta else Color.White
    }
    val elevation by transition.animateDp(label = "elevation") { isSelected ->
        if (isSelected) 10.dp else 2.dp
    }
    Surface(
        onClick = { selected = !selected },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(2.dp, borderColor),
        shadowElevation = elevation
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Hello, world!")
            // AnimatedVisibility as a part of the transition.
            transition.AnimatedVisibility(
                visible = { targetSelected -> targetSelected },
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Text(text = "It is fine today.")
            }
            // AnimatedContent as a part of the transition.
            transition.AnimatedContent { targetState ->
                if (targetState) {
                    Text(text = "Selected")
                } else {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone")
                }
            }
        }
    }
}

//----------------------------------------------------------------------
enum class BoxState { Collapsed, Expanded }

@Composable
fun AnimatingBox(boxState: BoxState) {
    val transitionData = updateTransitionData(boxState)
    // UI tree
    Box(
        modifier = Modifier
            .background(transitionData.color)
            .size(transitionData.size)
    )
}

// Holds the animation values.
private class TransitionData(
    color: State<Color>,
    size: State<Dp>
) {
    val color by color
    val size by size
}

// Create a Transition and return its animation values.
@Composable
private fun updateTransitionData(boxState: BoxState): TransitionData {
    val transition = updateTransition(boxState, label = "box state")
    val color = transition.animateColor(label = "color") { state ->
        when (state) {
            BoxState.Collapsed -> Color.Gray
            BoxState.Expanded -> Color.Red
        }
    }
    val size = transition.animateDp(label = "size") { state ->
        when (state) {
            BoxState.Collapsed -> 64.dp
            BoxState.Expanded -> 128.dp
        }
    }
    return remember(transition) { TransitionData(color, size) }
}
//----------------------------------------------------------------------

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun AnimatedVectorDrawable() {
    val image = AnimatedImageVector.animatedVectorResource(R.drawable.ic_baseline_hourglass)
    var atEnd by remember { mutableStateOf(false) }
    Image(
        painter = rememberAnimatedVectorPainter(image, atEnd),
        contentDescription = "Timer",
        modifier = Modifier.clickable {
            atEnd = !atEnd
        },
        contentScale = ContentScale.Crop
    )
}

@Composable
fun Gesture() {
    val offset = remember { Animatable(Offset(0f, 0f), Offset.VectorConverter) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                coroutineScope {
                    while (true) {
                        // Detect a tap event and obtain its position.
                        awaitPointerEventScope {
                            val position = awaitFirstDown().position

                            launch {
                                // Animate to the tap position.
                                offset.animateTo(position)
                            }
                        }
                    }
                }
            }
    ) {
        Circle(modifier = Modifier.offset { offset.value.toIntOffset() })
    }
}
@Composable
private fun Circle(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(Color.Blue))
}

private fun Offset.toIntOffset() = IntOffset(x.roundToInt(), y.roundToInt())

//---------------------------------------------------------------------------------
fun Modifier.swipeToDismiss(
    onDismissed: () -> Unit
): Modifier = composed {
    val offsetX = remember { Animatable(0f) }
    pointerInput(Unit) {
        // Used to calculate fling decay.
        val decay = splineBasedDecay<Float>(this)
        // Use suspend functions for touch events and the Animatable.
        coroutineScope {
            while (true) {
                val velocityTracker = VelocityTracker()
                // Stop any ongoing animation.
                offsetX.stop()
                awaitPointerEventScope {
                    // Detect a touch down event.
                    val pointerId = awaitFirstDown().id

                    horizontalDrag(pointerId) { change ->
                        // Update the animation value with touch events.
                        launch {
                            offsetX.snapTo(
                                offsetX.value + change.positionChange().x
                            )
                        }
                        velocityTracker.addPosition(
                            change.uptimeMillis,
                            change.position
                        )
                    }
                }
                // No longer receiving touch events. Prepare the animation.
                val velocity = velocityTracker.calculateVelocity().x
                val targetOffsetX = decay.calculateTargetValue(
                    offsetX.value,
                    velocity
                )
                // The animation stops when it reaches the bounds.
                offsetX.updateBounds(
                    lowerBound = -size.width.toFloat(),
                    upperBound = size.width.toFloat()
                )
                launch {
                    if (targetOffsetX.absoluteValue <= size.width) {
                        // Not enough velocity; Slide back.
                        offsetX.animateTo(
                            targetValue = 0f,
                            initialVelocity = velocity
                        )
                    } else {
                        // The element was swiped away.
                        offsetX.animateDecay(velocity, decay)
                        onDismissed()
                    }
                }
            }
        }
    }
        .offset { IntOffset(offsetX.value.roundToInt(), 0) }
}
//---------------------------------------------------------------------------------

//-------------------------------Custom Animations-------------------------------
@Composable
fun CustomAnimationAlphaFloatAsState(modifier: Modifier = Modifier) {
    var enabled by remember { mutableStateOf(true) }
    val alpha: Float by animateFloatAsState(
        targetValue = if (enabled) 1f else 0.5f,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "alpha" // For improved performance in complex animations
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Blue.copy(alpha = alpha)) // Apply the animated alpha
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { enabled = !enabled }) {
            Text(if (enabled) "Disable" else "Enable")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Example to demonstrate applying the animated alpha to Text
        Text("Animated Text", color = Color.Black.copy(alpha = alpha), style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun CustomAnimationSpringFloatAsState(modifier: Modifier = Modifier) {
    var targetValue by remember { mutableStateOf(0f) }
    val value by animateFloatAsState(
        targetValue = targetValue,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "" // No label is needed here as there is only one animation value being tracked
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {



        Box(
            modifier = Modifier
                .offset(x = value.dp * 200, y = 0.dp ) // Animate Horizontal offset
                //.offset(x = 0.dp, y = value.dp * 200 ) // Animate vertical offset
                .size(50.dp)
                .background(Color.Blue, CircleShape) // Using a circle shape
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { targetValue = if (targetValue == 0f) 1f else 0f } ) {
            Text("Animate")
        }
    }
}

@Composable
fun TweenAnimationWithDelayExample() {
    var targetValue by remember { mutableStateOf(0f) }
    val value by animateFloatAsState(
        targetValue = targetValue,
        animationSpec = tween(
            durationMillis = 300,
            delayMillis = 50,
            easing = LinearOutSlowInEasing
        ),

        label = ""// No label necessary for simple animations of one value

    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer { alpha = value }  // Animate alpha
                .background(Color.Blue)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { targetValue = if (targetValue == 0f) 1f else 0f }) {
            Text("Animate")
        }
    }
}

@Composable
fun KeyframesAnimationExample() {
    var targetValue by remember { mutableStateOf(0f) } // State to trigger animation
    val value by animateFloatAsState(
        targetValue = targetValue,
        animationSpec = keyframes {
            durationMillis = 375
            0.0f at 0 with LinearOutSlowInEasing // for 0-15 ms
            0.2f at 15 with FastOutLinearInEasing // for 15-75 ms
            0.4f at 75 // ms
            0.4f at 225 // ms  // Hold at 0.4f for a period
            1.0f at 375 with FastOutSlowInEasing // Final value, reached at 375ms
        },
        label = "" // Not needed for this example

    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer { alpha = value } // Use value to animate alpha
                .background(Color.Blue)
        )


        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {  targetValue = if (targetValue == 0f) 1f else 0f }) {
            Text("Animate")
        }

    }
}

@Composable
fun InfiniteRepeatableAnimationExample() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val value by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 300),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer { alpha = value }
                .background(Color.Blue)
        )

    }
}
data class MySize(val width: Dp, val height: Dp)
/*
@Composable
fun MyAnimation(targetSize: MySize) {
    val animSize: MySize by animateValueAsState(
        targetSize,
        TwoWayConverter(
            convertToVector = { size: MySize ->
                // Extract a float value from each of the `Dp` fields.
                AnimationVector2D(size.width.value, size.height.value)
            },
            convertFromVector = { vector: AnimationVector2D ->
                MySize(vector.v1.dp, vector.v2.dp)
            }
        ),
        label = "size"
    )
}
*/
@Composable
fun Custom2DAnimationExample() {
    var targetSize by remember { mutableStateOf(MySize(50.dp, 50.dp)) }
    val animSize: MySize by animateValueAsState(
        targetValue = targetSize,
        typeConverter = TwoWayConverter(
            convertToVector = { size: MySize ->
                AnimationVector2D(size.width.value, size.height.value)
            },
            convertFromVector = { vector: AnimationVector2D ->
                MySize(vector.v1.dp, vector.v2.dp)
            }
        ),
        animationSpec = spring(), // Default spring animation
        label = "" // Label not needed in this simple case
    )

    Box(
        modifier = Modifier
            .size(animSize.width, animSize.height) // Use the animated size
            .background(Color.Blue)
    )

    // Button to trigger animation (for demonstration):
    Button(onClick = {
        targetSize = if (targetSize.width == 50.dp) MySize(150.dp, 150.dp) else MySize(50.dp, 50.dp)
    }) {
        Text("Animate Size")
    }

}


data class My3DSize(val width: Dp, val height: Dp, val depth: Dp)


@Composable
fun Custom3DAnimationExample() {
    var targetSize by remember { mutableStateOf(My3DSize(50.dp, 50.dp, 0.dp)) }
    val animSize: My3DSize by animateValueAsState(
        targetValue = targetSize,
        typeConverter = TwoWayConverter(
            convertToVector = { size: My3DSize ->
                AnimationVector3D(size.width.value, size.height.value, size.depth.value)
            },
            convertFromVector = { vector: AnimationVector3D ->
                My3DSize(vector.v1.dp, vector.v2.dp, vector.v3.dp)
            }
        ),
        animationSpec = spring(),
        label = ""
    )

    Box(
        modifier = Modifier
            .size(animSize.width, animSize.height)
            .graphicsLayer {
                scaleX = 1f + animSize.depth.value / 100f // Simulate depth with scale
                scaleY = 1f + animSize.depth.value / 100f // Simulate depth with scale


            }
            .background(Color.Blue)
    )


    Button(onClick = {
        targetSize = if (targetSize.width == 50.dp) My3DSize(150.dp, 150.dp, 50.dp) else My3DSize(50.dp, 50.dp, 0.dp)
    }) {
        Text("Animate 3D Size")
    }

}