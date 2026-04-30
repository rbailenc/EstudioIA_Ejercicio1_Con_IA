package com.example.estudioia_ejercicio1_conia.data.Nerwork

import com.example.estudioia_ejercicio1_conia.data.model.AbilityDto
import com.example.estudioia_ejercicio1_conia.data.model.ResponseDto
import com.example.estudioia_ejercicio1_conia.data.model.PokemonFormDto
import com.example.estudioia_ejercicio1_conia.data.model.PokemonDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {
    @GET("pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ResponseDto

    @GET("pokemon-form/{id}")
    suspend fun getPokemonForm(
        @Path("id") id: Int
    ): PokemonFormDto

    @GET("pokemon/{id}")
    suspend fun getPokemonById(
        @Path("id") id: Int
    ): PokemonDetailDto

    @GET("ability/{id}")
    suspend fun getAbilityById(
        @Path("id") id: Int
    ): AbilityDto
}
