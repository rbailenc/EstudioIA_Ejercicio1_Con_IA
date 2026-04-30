package com.example.estudioia_ejercicio1_conia.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.estudioia_ejercicio1_conia.data.Repository.PokemonRepositoryImpl
import com.example.estudioia_ejercicio1_conia.domain.model.PokemonDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class PokemonDetailUiState {
    object Loading : PokemonDetailUiState()
    data class Success(val data: PokemonDetail) : PokemonDetailUiState()
    data class Error(val message: String) : PokemonDetailUiState()
}

@HiltViewModel
class PantallaDetalleViewModel @Inject constructor(
    private val repository: PokemonRepositoryImpl
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<PokemonDetailUiState>(PokemonDetailUiState.Loading)
    val uiState: StateFlow<PokemonDetailUiState> = _uiState

    fun loadPokemon(id: Int) {
        viewModelScope.launch {
            try {
                _uiState.value = PokemonDetailUiState.Loading
                val detail = repository.getPokemonDetail(id)
                _uiState.value = PokemonDetailUiState.Success(detail)
            } catch (e: Exception) {
                _uiState.value =
                    PokemonDetailUiState.Error(e.message ?: "Error cargando Pokémon")
            }
        }
    }
}
