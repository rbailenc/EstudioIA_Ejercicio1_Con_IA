package com.example.estudioia_ejercicio1_conia.domain.repository

import com.example.estudioia_ejercicio1_conia.domain.model.Pokemon

interface PokemonRepository {
    suspend fun getPokemons(
        limit: Int,
        offset: Int
    ): List<Pokemon>
}
