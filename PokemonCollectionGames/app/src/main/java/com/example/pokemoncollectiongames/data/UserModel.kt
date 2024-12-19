package com.example.pokemoncollectiongames.data

import androidx.annotation.StringRes
import com.example.pokemoncollectiongames.R

data class UserModel(
    @StringRes val title: Int = R.string.Home,
    val score: Int = 0
)
