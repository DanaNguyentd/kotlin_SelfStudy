package com.example.pokemonregion.pokemon

import androidx.annotation.DrawableRes

enum class Type {
    Grass,
    Poison,
    Flying,
    Fire,
    Water,
    Ghost,
    Dark,
    Fairy,
    Fighting,
    Psychic,
    Steel,
    Ground,
    Normal,
    Ice,
    Electric,
    Bug
}

data class Pokemon(
    val name: String,
    @DrawableRes val img: Int,
    val type: List<Type>
)
