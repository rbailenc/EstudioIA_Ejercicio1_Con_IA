package com.example.estudioia_ejercicio1_conia.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.animation.core.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
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
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(12.dp)
                    .border(
                        4.dp,
                        Color.Red,
                        RoundedCornerShape(20.dp)
                    )
                    .padding(12.dp)
            ) {

                // HEADER tipo Pokédex
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(Color(0xFF8B0000)),
                    contentAlignment = Alignment.Center
                ) {

                    val infiniteTransition = rememberInfiniteTransition()
                    val ledAlpha by infiniteTransition.animateFloat(
                        initialValue = 0.3f,
                        targetValue = 1f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(800),
                            repeatMode = RepeatMode.Reverse
                        )
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        // LEDs decorativos
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
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "#${detail.id.toString().padStart(3, '0')}",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = detail.name.uppercase(),
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        val scaleAnim by animateFloatAsState(
                            targetValue = 1f,
                            animationSpec = tween(600)
                        )

                        Box(
                            modifier = Modifier
                                .size(220.dp)
                                .background(
                                    brush = Brush.radialGradient(
                                        colors = listOf(
                                            Color(0xFF3B82F6),
                                            Color(0xFF1E3A8A)
                                        )
                                    )
                                )
                                .border(
                                    3.dp,
                                    Color.Red,
                                    RoundedCornerShape(12.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = detail.imageUrl,
                                contentDescription = detail.name,
                                modifier = Modifier
                                    .size(170.dp)
                                    .scale(scaleAnim)
                                    .alpha(0.95f)
                            )
                        }
                    }
                }

                // CARD de información
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .border(
                            3.dp,
                            Color.Red,
                            RoundedCornerShape(16.dp)
                        ),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF111111)
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = "Descripción",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Red
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = detail.description.replace("\n", " "),
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "Habilidades",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Red
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            detail.abilities.forEach { ability ->
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color.Black,
                                    modifier = Modifier.border(
                                        2.dp,
                                        Color.Red,
                                        RoundedCornerShape(8.dp)
                                    )
                                ) {
                                    Text(
                                        text = ability.replaceFirstChar { it.uppercase() },
                                        color = Color.White,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier.align(Alignment.End),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red,
                                contentColor = Color.White
                            )
                        ) {
                            Text("Volver")
                        }
                    }
                }
            }
        }
    }
}
