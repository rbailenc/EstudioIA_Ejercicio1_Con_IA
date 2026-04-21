package com.example.estudioia_ejercicio1_conia.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun Retrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}