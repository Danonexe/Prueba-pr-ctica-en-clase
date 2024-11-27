package com.example.pruebapracticaclase.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pruebapracticaclase.scenes.formulario
import com.example.pruebapracticaclase.scenes.registro


@Composable
fun AppNavigation(){
    val navControlador= rememberNavController()
    NavHost(navController= navControlador, startDestination = AppScene.formulario.route){
        composable(AppScene.formulario.route) {
            formulario(navControlador)
        }
        composable(
            AppScene.registro.route + "/{text}",
            arguments = listOf(navArgument(name = "text") {
                type = NavType.StringType
            })
        ) {
            registro(navControlador, it.arguments?.getString("text"))
        }
    }
}