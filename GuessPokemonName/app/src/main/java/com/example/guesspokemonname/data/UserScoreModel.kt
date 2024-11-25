package com.example.guesspokemonname.data

data class UserScoreModel(
    val pokemonList: List<String> = listOf(),
    val score: Int = 0
)
