package com.example.estudioia_ejercicio1_conia.data.model

data class ResponseDto(
    val count: Int,
    val netxt: String,
    val previous: String,
    val result: List<PokemonDto>
)
