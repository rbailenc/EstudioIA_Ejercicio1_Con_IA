package com.example.estudioia_ejercicio1_conia.di

import com.example.estudioia_ejercicio1_conia.data.Repository.PokemonRepositoryImpl
import com.example.estudioia_ejercicio1_conia.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Binds
    abstract fun bindPokemonRepository(
        impl: PokemonRepositoryImpl
    ): PokemonRepository
}