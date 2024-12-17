package dev.vishnuv.applebooksfab

import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Camera
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.vishnuv.applebooksfab.ui.theme.AppleBooksFABTheme
import dev.vishnuv.applebooksfab.ui.theme.Pink80

@Composable
fun AppleBooksFAB(modifier: Modifier = Modifier) {

    val config = LocalConfiguration.current
    val height = config.screenHeightDp
    val width = config.screenWidthDp

    var isVisible by remember { mutableStateOf(false) }


    val fabTween = tween<Float>(
        durationMillis = 700,
        easing = if (isVisible) FastOutSlowInEasing else EaseIn
    )

    val fabAnimation by animateFloatAsState(
        targetValue = if (isVisible) 0f else 1f,
        label = "FAB",
        animationSpec = fabTween
    )


    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            isVisible = !isVisible
        }, containerColor = Pink80,
            modifier = Modifier.graphicsLayer {
                alpha = fabAnimation
                scaleX = fabAnimation
                scaleY = fabAnimation
            }) {
            Icon(Icons.Default.Menu, contentDescription = "Menu")
        }
    }, modifier = Modifier.clickable {
        isVisible = false
    }) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                options.forEachIndexed { index, toggleOption ->
                    val option = options[index]

                    val menuAnimation by animateFloatAsState(
                        targetValue = if (isVisible) 1f else 0f,
                        animationSpec = keyframes {
                            durationMillis = 700
                            0.0f at (700 * (1 - (index / options.size.toFloat()))).toInt() using FastOutSlowInEasing
                            1.0f at (700 * (1 - (index / options.size.toFloat()))).toInt() using FastOutSlowInEasing
                        },
                        label = "Menu Animation"
                    )

                    val bottomOffset =
                        (height * menuAnimation * 0.06f * index).dp + fabPadding.dp

                    val rightOffset = (width * menuAnimation * 0.08f).dp + fabPadding.dp
                    Box(
                        Modifier
                            .align(Alignment.BottomEnd)
                            .offset(x = -rightOffset, y = -bottomOffset)
                            .alpha(if (menuAnimation == 0f) 0f else 1f)
                    ) {
                        if (index == 0) {
                            Row(
                                Modifier.width(width.dp * 0.6f),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Chip(icon = Icons.Outlined.Share)
                                Chip(icon = Icons.Outlined.Camera)
                                Chip(icon = Icons.Outlined.Share)
                                Chip(icon = Icons.Outlined.Bookmark)


                            }
                        } else {
                            Row(
                                Modifier
                                    .width(width.dp * 0.6f)
                                    .background(Pink80, shape = RoundedCornerShape(8.dp))
                                    .padding(12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(option.title)
                                Icon(option.icon, contentDescription = option.title)
                            }

                        }
                    }

                }
            }

        }
    }
}


@Composable
fun Chip(modifier: Modifier = Modifier, icon: ImageVector) {
    Box(
        modifier = modifier
            .background(Pink80, shape = RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) { Icon(icon, contentDescription = "Menu") }
}

@Preview
@Composable
private fun AppleBooksFABPreview() {
    AppleBooksFABTheme {
        AppleBooksFAB()
    }
}