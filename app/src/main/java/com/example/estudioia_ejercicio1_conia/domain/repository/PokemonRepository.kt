package com.example.estudioia_ejercicio1_conia.domain.repository

import com.example.estudioia_ejercicio1_conia.domain.model.Pokemon
import com.example.estudioia_ejercicio1_conia.domain.model.PokemonDetail

interface PokemonRepository {
    suspend fun getPokemons(
        limit: Int,
        offset: Int
    ): List<Pokemon>

    suspend fun getPokemonDetail(id: Int): PokemonDetail
}
