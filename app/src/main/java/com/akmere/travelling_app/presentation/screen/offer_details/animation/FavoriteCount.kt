package com.akmere.travelling_app.presentation.screen.offer_details.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

/**
 * Trecho de código retirado da página oficial android compose animation
 * @see <a href="https://developer.android.com/jetpack/compose/animation">android compose animation</a>
 */
@ExperimentalAnimationApi
@Composable
fun FavoriteCount(favoriteCount: String, modifier: Modifier) {
    AnimatedContent(
        targetState = favoriteCount,
        transitionSpec = {
            // Compare the incoming number with the previous number.
            if (targetState > initialState) {
                // If the target number is larger, it slides up and fades in
                // while the initial (smaller) number slides up and fades out.
                slideInVertically({ height -> height }) + fadeIn() with
                    slideOutVertically({ height -> -height }) + fadeOut()
            } else {
                // If the target number is smaller, it slides down and fades in
                // while the initial number slides down and fades out.
                slideInVertically({ height -> -height }) + fadeIn() with
                    slideOutVertically({ height -> height }) + fadeOut()
            }.using(
                // Disable clipping since the faded slide-in/out should
                // be displayed out of bounds.
                SizeTransform(clip = false)
            )
        }
    ) { value ->
        Text(
            text = value,
            modifier = modifier,
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colors.secondary,
        )
    }
}
