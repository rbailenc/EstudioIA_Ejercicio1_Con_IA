package com.example.estudioia_ejercicio1_conia.data.model

import com.google.gson.annotations.SerializedName

data class ResponseDto(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<PokemonDto>
)
