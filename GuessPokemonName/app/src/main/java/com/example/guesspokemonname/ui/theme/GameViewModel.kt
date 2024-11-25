package com.example.guesspokemonname.ui.theme

import androidx.lifecycle.ViewModel
import com.example.guesspokemonname.data.MAX_POKEMON_IN_GAME
import com.example.guesspokemonname.data.UserScoreModel
import com.example.guesspokemonname.data.allPokemons
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    // Game UI state
    private val _uiState = MutableStateFlow(UserScoreModel())
    val uiState: StateFlow<UserScoreModel> = _uiState.asStateFlow()


    init {
        resetGame()
    }

    /**
     * Reset the order state
     */
    fun resetGame() {
        _uiState.value = UserScoreModel(
            pokemonList = pickRandomPokemonsAndShuffle(),
            score = 0
        )
    }

    fun setScore(scoreFinal: Int) {
        _uiState.update{ currentState ->
            currentState.copy(score = scoreFinal)
        }
    }

    private fun pickRandomPokemonsAndShuffle(): List<String> {
        val PokemonList = allPokemons.shuffled()
        return PokemonList.subList(0, MAX_POKEMON_IN_GAME)
    }
}