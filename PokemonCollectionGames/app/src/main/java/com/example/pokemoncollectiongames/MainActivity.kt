package com.example.pokemoncollectiongames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pokemoncollectiongames.ui.theme.PokemonCollectionGamesTheme

import com.example.pokemoncollectiongames.data.parseJsonToModel
import com.example.pokemoncollectiongames.data.readJsonFromAssets
import com.example.pokemoncollectiongames.ui.theme.MainTheme

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val jsonString = readJsonFromAssets(this, getString(R.string.pokemonlist_json))
        val pokemonList = parseJsonToModel(jsonString)
        enableEdgeToEdge()
        setContent {
            val windowSize = calculateWindowSizeClass(this)
            PokemonCollectionGamesTheme {
                    MainTheme(
                        pokemonList,
                        windowSize = windowSize.widthSizeClass
                    )
            }
        }
    }
}