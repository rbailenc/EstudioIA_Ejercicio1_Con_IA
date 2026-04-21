package com.example.estudioia_ejercicio1_conia.data.Nerwork

import com.example.estudioia_ejercicio1_conia.data.model.ResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApiService {
    @GET("pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ResponseDto
}