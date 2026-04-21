package com.example.estudioia_ejercicio1_conia.domain.usecase

import androidx.compose.ui.geometry.Offset
import com.example.estudioia_ejercicio1_conia.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
){
    suspend operator fun invoke(
        limit: Int,
        offset: Int
    )=repository.getPokemons(limit, offset)
}