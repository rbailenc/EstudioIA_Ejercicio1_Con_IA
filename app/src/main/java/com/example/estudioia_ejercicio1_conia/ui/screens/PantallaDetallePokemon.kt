package com.example.estudioia_ejercicio1_conia.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.estudioia_ejercicio1_conia.ui.viewmodels.PantallaDetalleViewModel
import com.example.estudioia_ejercicio1_conia.ui.viewmodels.PokemonDetailUiState

@Composable
fun PantallaDetallePokemon(
    navController: NavController,
    pokemonId: Int,
    viewModel: PantallaDetalleViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.loadPokemon(pokemonId)
    }

    val state by viewModel.uiState.collectAsState()

    when (state) {

        is PokemonDetailUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is PokemonDetailUiState.Error -> {
            Text(
                text = (state as PokemonDetailUiState.Error).message,
                modifier = Modifier.padding(16.dp)
            )
        }

        is PokemonDetailUiState.Success -> {

            val detail = (state as PokemonDetailUiState.Success).data

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                // HEADER tipo Pokédex
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = "#${detail.id.toString().padStart(3, '0')}",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = detail.name.uppercase(),
                            style = MaterialTheme.typography.headlineLarge
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        AsyncImage(
                            model = detail.imageUrl,
                            contentDescription = detail.name,
                            modifier = Modifier.size(180.dp)
                        )
                    }
                }

                // CARD de información
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    shape = MaterialTheme.shapes.large,
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = "Descripción",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = detail.description.replace("\n", " "),
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "Habilidades",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            detail.abilities.forEach { ability ->
                                Surface(
                                    shape = MaterialTheme.shapes.medium,
                                    tonalElevation = 2.dp
                                ) {
                                    Text(
                                        text = ability.replaceFirstChar { it.uppercase() },
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text("Volver")
                        }
                    }
                }
            }
        }
    }
}
