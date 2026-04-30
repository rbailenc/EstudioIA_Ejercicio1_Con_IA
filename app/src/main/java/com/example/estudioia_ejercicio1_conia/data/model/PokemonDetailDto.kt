package com.example.estudioia_ejercicio1_conia.data.model

data class PokemonDetailDto(
    val id: Int,
    val name: String,
    val abilities: List<AbilitySlotDto>,
    val sprites: SpritesDto,
    val height: Int,
    val weight: Int,
    val types: List<TypeSlotDto>
)

data class AbilitySlotDto(
    val ability: AbilityDto
)

data class TypeSlotDto(
    val type: TypeDto
)

data class TypeDto(
    val name: String
)
