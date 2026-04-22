package com.example.estudioia_ejercicio1_conia.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.estudioia_ejercicio1_conia.ui.viewmodels.PantallaPrincipalViewmodel
import com.example.estudioia_ejercicio1_conia.ui.viewmodels.PokemonUiState

@Composable
fun PantallaInicio(
    navController: NavController,
    viewModel: PantallaPrincipalViewmodel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {

        when (val state = uiState) {

            is PokemonUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is PokemonUiState.Error -> {
                Text(
                    text = state.message,
                    modifier = Modifier.padding(16.dp)
                )
            }

            is PokemonUiState.Success -> {

                val pokemons = state.data

                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(pokemons) { pokemon ->
                        Text(
                            text = pokemon.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate("detalle/${pokemon.name}")
                                }
                                .padding(16.dp)
                        )
                        HorizontalDivider()
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    if (!viewModel.esPrimeraPagina()) {
                        Button(onClick = { viewModel.paginaAnterior() }) {
                            Text("Anterior")
                        }
                    }

                    Button(onClick = { viewModel.siguientePagina() }) {
                        Text("Siguiente")
                    }
                }
            }
        }
    }
}
