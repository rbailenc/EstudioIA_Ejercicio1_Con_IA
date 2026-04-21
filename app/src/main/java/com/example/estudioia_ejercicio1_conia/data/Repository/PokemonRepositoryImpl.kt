package com.example.estudioia_ejercicio1_conia.data.Repository

import com.example.estudioia_ejercicio1_conia.data.Nerwork.PokemonApiService
import com.example.estudioia_ejercicio1_conia.data.model.ResponseDto
import com.example.estudioia_ejercicio1_conia.domain.repository.PokemonRepository
import jakarta.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api : PokemonApiService
) : PokemonRepository {
    override suspend fun getPokemons(
        limit: Int,
        offset: Int
    ): ResponseDto {
        return api.getPokemons(limit, offset)
    }
}