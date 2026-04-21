package com.example.estudioia_ejercicio1_conia.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.estudioia_ejercicio1_conia.ui.theme.EstudioIAEjercicio1ConIATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EstudioIAEjercicio1ConIATheme {

            }
        }
    }
}