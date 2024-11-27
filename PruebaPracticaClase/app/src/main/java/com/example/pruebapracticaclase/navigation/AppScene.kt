package com.example.pruebapracticaclase.navigation

sealed class AppScene (val route: String) {
    object formulario: AppScene("formulario")
    object registro: AppScene("registro")
}