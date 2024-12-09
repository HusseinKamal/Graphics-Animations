package com.hussein.animationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hussein.animationcompose.compsoe.AddOvalImage
import com.hussein.animationcompose.compsoe.AnimateFloatAsState
import com.hussein.animationcompose.compsoe.AnimateFloatAsStateValue
import com.hussein.animationcompose.compsoe.AnimatePosition
import com.hussein.animationcompose.compsoe.AnimatedMorph
import com.hussein.animationcompose.compsoe.AnimatedVectorDrawable
import com.hussein.animationcompose.compsoe.AnimatingBox
import com.hussein.animationcompose.compsoe.AnimationContentSize
import com.hussein.animationcompose.compsoe.AnimationContentWithBorders
import com.hussein.animationcompose.compsoe.AnimationNavigation
import com.hussein.animationcompose.compsoe.AnimationPadding
import com.hussein.animationcompose.compsoe.AnimationPositionsRelative
import com.hussein.animationcompose.compsoe.AnimationShadow
import com.hussein.animationcompose.compsoe.AnimationSlidingTopDownText
import com.hussein.animationcompose.compsoe.AnimationSurfaceClick
import com.hussein.animationcompose.compsoe.AnimationText
import com.hussein.animationcompose.compsoe.AnimationVisibility
import com.hussein.animationcompose.compsoe.AnimationVisibilityFade
import com.hussein.animationcompose.compsoe.AspectRatioImage
import com.hussein.animationcompose.compsoe.BlackWhiteImage
import com.hussein.animationcompose.compsoe.BlurImage
import com.hussein.animationcompose.compsoe.BorderedImage
import com.hussein.animationcompose.compsoe.BoxState
import com.hussein.animationcompose.compsoe.CanvasCompose
import com.hussein.animationcompose.compsoe.CircleCompose
import com.hussein.animationcompose.compsoe.ClickableMorph
import com.hussein.animationcompose.compsoe.ClipShape
import com.hussein.animationcompose.compsoe.ColoredBorderImage
import com.hussein.animationcompose.compsoe.CompositingStrategyExamples
import com.hussein.animationcompose.compsoe.CompositingStrategy_ModulateAlpha
import com.hussein.animationcompose.compsoe.ContrastBrightnessImage
import com.hussein.animationcompose.compsoe.Custom2DAnimationExample
import com.hussein.animationcompose.compsoe.Custom3DAnimationExample
import com.hussein.animationcompose.compsoe.CustomAnimationAlphaFloatAsState
import com.hussein.animationcompose.compsoe.CustomAnimationSpringFloatAsState
import com.hussein.animationcompose.compsoe.CustomizedOverlayImages
import com.hussein.animationcompose.compsoe.DrawBackgroundTextMeasured
import com.hussein.animationcompose.compsoe.DrawBackgroundTextOverFlow
import com.hussein.animationcompose.compsoe.DrawChooseContent
import com.hussein.animationcompose.compsoe.DrawClickableLayer
import com.hussein.animationcompose.compsoe.DrawColoredShape
import com.hussein.animationcompose.compsoe.DrawImage
import com.hussein.animationcompose.compsoe.DrawImageScaleIncrease
import com.hussein.animationcompose.compsoe.DrawImageWithActiveCircle
import com.hussein.animationcompose.compsoe.DrawImageWithRotation
import com.hussein.animationcompose.compsoe.DrawInvertedPath
import com.hussein.animationcompose.compsoe.DrawPath
import com.hussein.animationcompose.compsoe.DrawText
import com.hussein.animationcompose.compsoe.DrawTextWithGradient
import com.hussein.animationcompose.compsoe.FlipText
import com.hussein.animationcompose.compsoe.Gesture
import com.hussein.animationcompose.compsoe.HeartShape
import com.hussein.animationcompose.compsoe.ImageAlpha
import com.hussein.animationcompose.compsoe.ImageContainer
import com.hussein.animationcompose.compsoe.InfiniteRepeatableAnimationExample
import com.hussein.animationcompose.compsoe.InsetCanvas
import com.hussein.animationcompose.compsoe.InvertedImage
import com.hussein.animationcompose.compsoe.KeyframesAnimationExample
import com.hussein.animationcompose.compsoe.LineCompose
import com.hussein.animationcompose.compsoe.MainContent
import com.hussein.animationcompose.compsoe.RotatedCanvas
import com.hussein.animationcompose.compsoe.RotatingScallopedProfilePic
import com.hussein.animationcompose.compsoe.RoundedCornerImage
import com.hussein.animationcompose.compsoe.RoundedImage
import com.hussein.animationcompose.compsoe.SharedElement_PredictiveBack
import com.hussein.animationcompose.compsoe.TintImage
import com.hussein.animationcompose.compsoe.TransFormedRotatedCanvas
import com.hussein.animationcompose.compsoe.TranslatedCircle
import com.hussein.animationcompose.compsoe.TweenAnimationWithDelayExample
import com.hussein.animationcompose.ui.theme.AnimationComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimationComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //------------------------Images------------------------
                    //ImageContainer(modifier = Modifier.padding(innerPadding))
                    //RoundedImage(modifier = Modifier.padding(innerPadding))
                    // RoundedCornerImage(modifier = Modifier.padding(innerPadding))
                    //AddOvalImage(modifier = Modifier.padding(innerPadding))
                    //BorderedImage(modifier = Modifier.padding(innerPadding))
                    //ColoredBorderImage(modifier = Modifier.padding(innerPadding))
                    //AspectRatioImage(modifier = Modifier.padding(innerPadding))
                    //TintImage(modifier = Modifier.padding(innerPadding))
                    //BlackWhiteImage(modifier = Modifier.padding(innerPadding))
                    //ContrastBrightnessImage(modifier = Modifier.padding(innerPadding))
                    //InvertedImage(modifier = Modifier.padding(innerPadding))
                    //BlurImage(modifier = Modifier.padding(innerPadding))
                    //CustomizedOverlayImages(modifier = Modifier.padding(innerPadding))

                    //------------------------Graphics------------------------
                    //CanvasCompose(modifier = Modifier.padding(innerPadding))
                    //LineCompose(modifier = Modifier.padding(innerPadding))
                    //TranslatedCircle(modifier = Modifier.padding(innerPadding))
                    //RotatedCanvas(modifier = Modifier.padding(innerPadding))
                    //InsetCanvas(modifier = Modifier.padding(innerPadding))
                    //TransFormedRotatedCanvas(modifier = Modifier.padding(innerPadding))
                    //DrawText(modifier = Modifier.padding(innerPadding))
                    //DrawBackgroundTextMeasured(modifier = Modifier.padding(innerPadding))
                    //DrawBackgroundTextOverFlow(modifier = Modifier.padding(innerPadding))
                    //DrawImage(modifier = Modifier.padding(innerPadding))
                    //DrawPath(modifier = Modifier.padding(innerPadding))
                    //DrawInvertedPath(modifier = Modifier.padding(innerPadding)) //DrawColoredShape(modifier = Modifier.padding(innerPadding))
                    //DrawColoredShape(modifier = Modifier.padding(innerPadding))
                    //DrawChooseContent(modifier = Modifier.padding(innerPadding))
                    //DrawTextWithGradient(modifier = Modifier.padding(innerPadding))
                    //DrawImageScaleIncrease(modifier = Modifier.padding(innerPadding))
                    //DrawImageTranslate(modifier = Modifier.padding(innerPadding))
                    //DrawImageWithRotation(modifier = Modifier.padding(innerPadding))
                    //ClipShape(modifier = Modifier.padding(innerPadding))
                    //ImageAlpha(modifier = Modifier.padding(innerPadding))
                    //DrawImageWithActiveCircle(modifier = Modifier.padding(innerPadding))
                    //CompositingStrategy_ModulateAlpha()
                    //DrawClickableLayer()
                    //FlipText()
                    //AnimatedMorph(modifier = Modifier.padding(innerPadding))
                    //ClickableMorph()
                    //RotatingScallopedProfilePic()
                    //HeartShape()

                    //Animations
                    val scrollState = rememberScrollState()
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .verticalScroll(scrollState) // Makes the column horizontally scrollable
                    ) {
                        //AnimateFloatAsState(modifier = Modifier.padding(innerPadding))
                        //AnimationContentSize(modifier = Modifier.padding(innerPadding))
                        //AnimatePosition(modifier = Modifier.padding(innerPadding))
                        //AnimationPositionsRelative(modifier = Modifier.padding(innerPadding))
                        //AnimationPadding(modifier = Modifier.padding(innerPadding))
                        //AnimationShadow(modifier = Modifier.padding(innerPadding))
                        //AnimationText(modifier = Modifier.padding(innerPadding))
                        //AnimationNavigation(modifier = Modifier.padding(innerPadding))
                        //AnimationVisibility(modifier = Modifier.padding(innerPadding))
                        //AnimationVisibilityFade(modifier = Modifier.padding(innerPadding))
                        //AnimationSlidingTopDownText(modifier = Modifier.padding(innerPadding))
                        //AnimationSurfaceClick(modifier = Modifier.padding(innerPadding))
                        //AnimateFloatAsStateValue(modifier = Modifier.padding(innerPadding))
                        //AnimationContentWithBorders(modifier = Modifier.padding(innerPadding))
                        //AnimatingBox(BoxState.Collapsed)
                        //AnimatedVectorDrawable()
                        //Gesture()
                        //CustomAnimationAlphaFloatAsState(modifier = Modifier.padding(innerPadding))
                        //CustomAnimationSpringFloatAsState(modifier = Modifier.padding(innerPadding))
                        //TweenAnimationWithDelayExample()
                        //KeyframesAnimationExample()
                        //InfiniteRepeatableAnimationExample()
                        //MyAnimation(targetSize = MySize(100.dp, 100.dp))
                        //Custom2DAnimationExample()
                        //Custom3DAnimationExample()

                        //Shared Element Transition
//                        MainContent(onShowDetails = {},
//                            modifier = Modifier.padding(innerPadding),
//                            sharedTransitionScope = this,
//                            animatedVisibilityScope = this)
                        SharedElement_PredictiveBack()
                    }
                }
            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        AnimationComposeTheme {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                //------------------------Images------------------------
                //ImageContainer(modifier = Modifier.padding(innerPadding))
                //RoundedImage(modifier = Modifier.padding(innerPadding))
                //RoundedCornerImage(modifier = Modifier.padding(innerPadding))
                //AddOvalImage(modifier = Modifier.padding(innerPadding))
                //BorderedImage(modifier = Modifier.padding(innerPadding))
                //ColoredBorderImage(modifier = Modifier.padding(innerPadding))
                //AspectRatioImage(modifier = Modifier.padding(innerPadding))
                //TintImage(modifier = Modifier.padding(innerPadding))
                //BlackWhiteImage(modifier = Modifier.padding(innerPadding))
                //ContrastBrightnessImage(modifier = Modifier.padding(innerPadding))
                //InvertedImage(modifier = Modifier.padding(innerPadding))
                //BlurImage(modifier = Modifier.padding(innerPadding))
                //CustomizedOverlayImages(modifier = Modifier.padding(innerPadding))

                //------------------------Graphics ------------------------
                //CanvasCompose(modifier = Modifier.padding(innerPadding))
                //LineCompose(modifier = Modifier.padding(innerPadding))
                //CircleCompose(modifier = Modifier.padding(innerPadding))
                //TranslatedCircle(modifier = Modifier.padding(innerPadding))
                //RotatedCanvas(modifier = Modifier.padding(innerPadding))
                //InsetCanvas(modifier = Modifier.padding(innerPadding))
                //TransFormedRotatedCanvas(modifier = Modifier.padding(innerPadding))
                //DrawText(modifier = Modifier.padding(innerPadding))
                //DrawBackgroundTextMeasured(modifier = Modifier.padding(innerPadding))
                //DrawBackgroundTextOverFlow(modifier = Modifier.padding(innerPadding))
                //DrawImage(modifier = Modifier.padding(innerPadding))
                //DrawPath(modifier = Modifier.padding(innerPadding))
                //DrawInvertedPath(modifier = Modifier.padding(innerPadding))
                //DrawColoredShape(modifier = Modifier.padding(innerPadding))
                //DrawChooseContent(modifier = Modifier.padding(innerPadding))
                // DrawTextWithGradient(modifier = Modifier.padding(innerPadding))
                //DrawImageScaleIncrease(modifier = Modifier.padding(innerPadding))
                //DrawImageTranslate(modifier = Modifier.padding(innerPadding))
                //DrawImageWithRotation(modifier = Modifier.padding(innerPadding))
                //ClipShape(modifier = Modifier.padding(innerPadding))
                //ImageAlpha(modifier = Modifier.padding(innerPadding))
                //DrawImageWithActiveCircle(modifier = Modifier.padding(innerPadding))
                //CompositingStrategyExamples()
                //CompositingStrategy_ModulateAlpha()
                //DrawClickableLayer()
                //FlipText()
            }
        }
    }
}


