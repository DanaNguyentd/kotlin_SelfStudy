package com.example.pokemonregion.region

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

import com.example.pokemonregion.pokemon.Pokemon

data class Region(
    val nameReg: String,
    @DrawableRes val imageReg: Int,
    @StringRes val detailReg: Int,
    val pokemonRegList: List<Pokemon>?
)
