package com.example.mycoursesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.core.ui.theme.Dark
import com.example.mycoursesapp.navigation.AppNavigation
import com.example.mycoursesapp.ui.theme.MyCoursesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.Black.toArgb()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                Dark.toArgb()
            ),
            navigationBarStyle = SystemBarStyle.dark(
                Dark.toArgb()
            )
        )
        setContent {
            MyCoursesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}