package com.akmere.travelling_app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.compose.rememberNavController
import com.akmere.travelling_app.presentation.common.AppTheme
import com.akmere.travelling_app.presentation.screen.home.components.ScreenNavigator

@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            AppTheme {
                ScreenNavigator(navController = navController)
            }
        }
    }
}
