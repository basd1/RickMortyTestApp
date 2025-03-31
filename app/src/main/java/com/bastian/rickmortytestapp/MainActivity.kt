package com.bastian.rickmortytestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.bastian.rickmortytestapp.presentation.navigation.NavGraph
import com.bastian.rickmortytestapp.ui.theme.RickMortyTestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickMortyTestAppTheme {
                
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
