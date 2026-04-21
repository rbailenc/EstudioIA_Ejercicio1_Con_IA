package com.example.estudioia_ejercicio1_conia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import com.example.estudioia_ejercicio1_conia.ui.screens.PantallaDetallePokemon
import com.example.estudioia_ejercicio1_conia.ui.screens.PantallaInicio
import com.example.estudioia_ejercicio1_conia.ui.theme.EstudioIAEjercicio1ConIATheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EstudioIAEjercicio1ConIATheme {
                NavController()
            }
        }
    }
}

@Composable
fun NavController(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            PantallaInicio(navController)
        }

        composable("detail") {
            PantallaDetallePokemon(navController)
        }
    }

}