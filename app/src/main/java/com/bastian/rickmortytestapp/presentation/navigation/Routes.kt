package com.bastian.rickmortytestapp.presentation.navigation

sealed class Routes(val route: String) {
    data object Chars : Routes("characters")
    data object Detail : Routes("detail/{id}"){
        fun createRoute(id: Int) = "detail/$id"
    }
}