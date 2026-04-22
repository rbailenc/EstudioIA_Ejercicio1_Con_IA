package com.example.estudioia_ejercicio1_conia.data.Repository

import com.example.estudioia_ejercicio1_conia.data.Nerwork.PokemonApiService
import com.example.estudioia_ejercicio1_conia.data.mapper.toDomainList
import com.example.estudioia_ejercicio1_conia.domain.model.Pokemon
import com.example.estudioia_ejercicio1_conia.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApiService
) : PokemonRepository {

    override suspend fun getPokemons(
        limit: Int,
        offset: Int
    ): List<Pokemon> {
        val response = api.getPokemons(limit, offset)
        return response.result.toDomainList()
    }
}
