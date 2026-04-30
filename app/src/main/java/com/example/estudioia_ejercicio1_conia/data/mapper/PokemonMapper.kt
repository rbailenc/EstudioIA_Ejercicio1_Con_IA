package com.example.estudioia_ejercicio1_conia.data.mapper

import com.example.estudioia_ejercicio1_conia.data.model.PokemonDto
import com.example.estudioia_ejercicio1_conia.domain.model.Pokemon

fun PokemonDto.toDomain(): Pokemon {
    val id = url
        .trimEnd('/')
        .substringAfterLast('/')
        .toInt()

    return Pokemon(
        id = id,
        name = name,
        url = url,
        imageUrl = null
    )
}

fun List<PokemonDto>.toDomainList(): List<Pokemon> {
    return this.map { it.toDomain() }
}
