@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.hussein.animationcompose.compsoe

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hussein.animationcompose.R

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainContent(
    onShowDetails: () -> Unit,
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    with(sharedTransitionScope) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .sharedBounds(
                    rememberSharedContentState(key = "bounds"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    resizeMode = SharedTransitionScope.ResizeMode.ScaleToBounds()
                )
            // ...
        ) {
            // ...
        }
    }
}

@Composable
private fun DetailsContent(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    with(sharedTransitionScope) {
        Column(
            modifier = Modifier
                .padding(top = 200.dp, start = 16.dp, end = 16.dp)
                .sharedBounds(
                    rememberSharedContentState(key = "bounds"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    resizeMode = SharedTransitionScope.ResizeMode.ScaleToBounds()
                )
            // ...

        ) {
            // ...
        }
    }
}
//--------------------Navigation Example --------------------

@Preview
@Composable
fun SharedElement_PredictiveBack() {
    SharedTransitionLayout {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") {
                HomeScreen(
                    navController,
                    this@SharedTransitionLayout,
                    this@composable
                )
            }
            composable(
                "details/{item}",
                arguments = listOf(navArgument("item") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("item")
                val snack = listSnacks[id!!]
                DetailsScreen(
                    navController,
                    id,
                    snack,
                    this@SharedTransitionLayout,
                    this@composable
                )
            }
        }
    }
}

@Composable
private fun DetailsScreen(
    navController: NavHostController,
    id: Int,
    snack: Snack,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    with(sharedTransitionScope) {
        Column(
            Modifier
                .fillMaxWidth()
                .height(1000.dp)
                .clickable {
                    navController.navigate("home")
                }
        ) {
            Image(
                painterResource(id = snack.image),
                contentDescription = snack.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = "image-$id"),
                        animatedVisibilityScope = animatedContentScope
                    )
                    .aspectRatio(1f)
                    .fillMaxWidth()
            )
            Text(
                snack.name, fontSize = 18.sp,
                modifier =
                Modifier
                    .sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = "text-$id"),
                        animatedVisibilityScope = animatedContentScope
                    )
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
private fun HomeScreen(
    navController: NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    LazyColumn(
        modifier = Modifier
            .height(1000.dp)
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(listSnacks) { index, item ->
            Row(
                Modifier.clickable {
                    navController.navigate("details/$index")
                }
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                with(sharedTransitionScope) {
                    Image(
                        painterResource(id = item.image),
                        contentDescription = item.description,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .sharedElement(
                                sharedTransitionScope.rememberSharedContentState(key = "image-$index"),
                                animatedVisibilityScope = animatedContentScope
                            )
                            .size(100.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        item.name, fontSize = 18.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .sharedElement(
                                sharedTransitionScope.rememberSharedContentState(key = "text-$index"),
                                animatedVisibilityScope = animatedContentScope,
                            )
                    )
                }
            }
        }
    }
}

data class Snack(
    val name: String,
    val description: String,
    @DrawableRes val image: Int
)

var listSnacks = arrayListOf(Snack("Snack 0", "Description 1", R.drawable.ic_launcher_background),
    Snack("Snack 1", "Description 1", R.drawable.ic_launcher_background),
    Snack("Snack 2", "Description 1", R.drawable.img),
    Snack("Snack 3", "Description 1", R.drawable.img_1),
    Snack("Snack 4", "Description 1", R.drawable.ic_launcher_background),
    Snack("Snack 5", "Description 1", R.drawable.ic_launcher_background),
    Snack("Snack 6", "Description 1", R.drawable.ic_launcher_background),
    Snack("Snack 7", "Description 1", R.drawable.ic_launcher_background)
)