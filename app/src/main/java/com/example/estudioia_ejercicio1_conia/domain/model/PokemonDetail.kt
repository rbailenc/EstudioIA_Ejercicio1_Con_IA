package com.example.estudioia_ejercicio1_conia.domain.model

data class PokemonDetail(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String?,
    val abilities: List<String>,
    val height: Int,
    val weight: Int,
    val types: List<String>
)
