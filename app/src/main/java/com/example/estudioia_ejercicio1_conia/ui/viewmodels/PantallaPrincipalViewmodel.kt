package com.example.estudioia_ejercicio1_conia.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.estudioia_ejercicio1_conia.domain.model.Pokemon
import com.example.estudioia_ejercicio1_conia.domain.usecase.GetPokemonUseCase
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class PokemonUiState {
    object Loading : PokemonUiState()
    data class Success(val data: List<Pokemon>) : PokemonUiState()
    data class Error(val message: String) : PokemonUiState()
}

class PantallaPrincipalViewmodel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
) : ViewModel() {

    private val pageSize = 20
    private var currentOffset = 0

    private val _uiState = MutableStateFlow<PokemonUiState>(PokemonUiState.Loading)
    val uiState: StateFlow<PokemonUiState> = _uiState.asStateFlow()

    init {
        cargarPokemons()
    }

    fun cargarPokemons() {
        viewModelScope.launch {
            _uiState.value = PokemonUiState.Loading
            try {
                val pokemons = getPokemonUseCase(pageSize, currentOffset)
                _uiState.value = PokemonUiState.Success(pokemons)
            } catch (e: Exception) {
                _uiState.value = PokemonUiState.Error(
                    e.message ?: "Error desconocido"
                )
            }
        }
    }

    fun siguientePagina() {
        currentOffset += pageSize
        cargarPokemons()
    }

    fun paginaAnterior() {
        if (currentOffset >= pageSize) {
            currentOffset -= pageSize
            cargarPokemons()
        }
    }

    fun esPrimeraPagina(): Boolean = currentOffset == 0
}
