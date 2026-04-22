package com.example.estudioia_ejercicio1_conia.data.model

import com.google.gson.annotations.SerializedName

data class AbilityDto(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String? = null,
    @SerializedName("effect_entries") val effectEntries : List<EffectEntrieDto>? = null
)
