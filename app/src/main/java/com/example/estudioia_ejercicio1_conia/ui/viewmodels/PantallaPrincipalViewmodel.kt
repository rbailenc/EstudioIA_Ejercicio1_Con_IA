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
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PokemonUiState>(PokemonUiState.Loading)
    val uiState: StateFlow<PokemonUiState> = _uiState.asStateFlow()

    fun cargarPokemons(limit: Int = 20, offset: Int = 0) {
        viewModelScope.launch {
            _uiState.value = PokemonUiState.Loading
            try {
                val pokemons = getPokemonUseCase(limit, offset)
                _uiState.value = PokemonUiState.Success(pokemons)
            } catch (e: Exception) {
                _uiState.value = PokemonUiState.Error(
                    e.message ?: "Error desconocido"
                )
            }
        }
    }
}
