package com.example.estudioia_ejercicio1_conia.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.animation.core.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.draw.scale
import coil.compose.AsyncImage
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(12.dp)
            .border(4.dp, Color.Red, RoundedCornerShape(20.dp))
            .padding(12.dp)
    ) {

        when (val state = uiState) {

            is PokemonUiState.Loading -> {

                val infiniteTransition = rememberInfiniteTransition()
                val alpha by infiniteTransition.animateFloat(
                    initialValue = 0.3f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(800),
                        repeatMode = RepeatMode.Reverse
                    )
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "POKEDEX BOOTING...",
                        color = Color.Red,
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .background(
                                Brush.radialGradient(
                                    colors = listOf(
                                        Color(0xFF3B82F6),
                                        Color(0xFF1E3A8A)
                                    )
                                )
                            )
                            .border(3.dp, Color.Red, RoundedCornerShape(12.dp))
                            .alpha(alpha)
                    )
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

                // Header hardware
                val infiniteTransition = rememberInfiniteTransition()
                val ledAlpha by infiniteTransition.animateFloat(
                    initialValue = 0.3f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(800),
                        repeatMode = RepeatMode.Reverse
                    )
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(3) {
                        Box(
                            modifier = Modifier
                                .size(14.dp)
                                .background(
                                    Color.Green.copy(alpha = ledAlpha),
                                    CircleShape
                                )
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "POKEDEX",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(pokemons) { pokemon ->

                        val scaleAnim by animateFloatAsState(
                            targetValue = 1f,
                            animationSpec = tween(300)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .border(
                                    2.dp,
                                    Color.Red,
                                    RoundedCornerShape(12.dp)
                                )
                                .background(Color(0xFF111111))
                                .clickable {
                                    navController.navigate("detalle/${pokemon.id}")
                                }
                                .padding(12.dp)
                                .scale(scaleAnim),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "#${pokemon.id.toString().padStart(3, '0')}",
                                color = Color.Red,
                                modifier = Modifier.width(60.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .background(
                                        Brush.radialGradient(
                                            colors = listOf(
                                                Color(0xFF3B82F6),
                                                Color(0xFF1E3A8A)
                                            )
                                        )
                                    )
                                    .border(
                                        2.dp,
                                        Color.Red,
                                        RoundedCornerShape(8.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                AsyncImage(
                                    model = pokemon.imageUrl,
                                    contentDescription = pokemon.name,
                                    modifier = Modifier.size(48.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = pokemon.name,
                                color = Color.White
                            )
                        }
                    }
                }

                if (viewModel.esPrimeraPagina()){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.End
                    ) {

                        Button(
                            onClick = { viewModel.siguientePagina() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red,
                                contentColor = Color.White
                            )
                        ) {
                            Text("Siguiente")
                        }
                    }
                }else{
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Button(
                            onClick = { viewModel.paginaAnterior() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red,
                                contentColor = Color.White
                            )
                        ) {
                            Text("Anterior")
                        }

                        Button(
                            onClick = { viewModel.siguientePagina() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red,
                                contentColor = Color.White
                            )
                        ) {
                            Text("Siguiente")
                        }
                    }
                }
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp))
            }
        }
    }
}
