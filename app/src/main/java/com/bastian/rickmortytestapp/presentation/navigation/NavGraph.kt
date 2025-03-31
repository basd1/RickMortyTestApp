package com.bastian.rickmortytestapp.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bastian.rickmortytestapp.presentation.detail.DetailScreen
import com.bastian.rickmortytestapp.presentation.list.CharListScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Chars.route) {

        composable(Routes.Chars.route) {
            CharListScreen(navController)
        }

        composable(Routes.Detail.route) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            if (characterId != null) {
                DetailScreen(navController, characterId)
            } else {
                Text("Error: Character ID inválido")
            }
        }
    }
}