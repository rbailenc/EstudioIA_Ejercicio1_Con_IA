package com.example.estudioia_ejercicio1_conia.data.Repository

import com.example.estudioia_ejercicio1_conia.data.Nerwork.PokemonApiService
import com.example.estudioia_ejercicio1_conia.domain.model.Pokemon
import com.example.estudioia_ejercicio1_conia.domain.model.PokemonDetail
import com.example.estudioia_ejercicio1_conia.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApiService
) : PokemonRepository {

    override suspend fun getPokemonDetail(id: Int): PokemonDetail {

        val pokemonDto = api.getPokemonById(id)

        val abilitiesNames = mutableListOf<String>()
        var description = ""

        pokemonDto.abilities.forEach { slot ->
            val abilityUrl = slot.ability.url
            if (abilityUrl != null) {
                val abilityId = abilityUrl
                    .trimEnd('/')
                    .substringAfterLast('/')
                    .toInt()

                val abilityDetail = api.getAbilityById(abilityId)

                abilitiesNames.add(slot.ability.name)

                if (description.isEmpty()) {
                    description = abilityDetail.effectEntries
                        ?.firstOrNull()?.effect ?: ""
                }
            }
        }

        return PokemonDetail(
            id = pokemonDto.id,
            name = pokemonDto.name,
            description = description,
            imageUrl = pokemonDto.sprites.frontDefault,
            abilities = abilitiesNames
        )
    }

    override suspend fun getPokemons(
        limit: Int,
        offset: Int
    ): List<Pokemon> {
        val response = api.getPokemons(limit, offset)

        return response.results.map { dto ->

            val id = dto.url
                .trimEnd('/')
                .substringAfterLast('/')
                .toInt()

            val form = api.getPokemonForm(id)
            val imageUrl = form.sprites.frontDefault

            Pokemon(
                id = id,
                name = dto.name,
                url = dto.url,
                imageUrl = imageUrl
            )
        }
    }
}
