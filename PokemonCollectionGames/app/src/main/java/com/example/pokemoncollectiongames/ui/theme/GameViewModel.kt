package com.example.pokemoncollectiongames.ui.theme

import androidx.lifecycle.ViewModel
import com.example.pokemoncollectiongames.R
import com.example.pokemoncollectiongames.data.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    // Game UI state
    private val _uiState = MutableStateFlow(UserModel())
    val uiState: StateFlow<UserModel> = _uiState.asStateFlow()

    init {
        resetGame()
    }

    /**
     * Reset the order state
     */
    fun resetGame() {
        _uiState.value = UserModel(
            title = R.string.Home,
            score = 0
        )
    }

    fun setScore(scoreFinal: Int) {
        _uiState.update{ currentState ->
            currentState.copy(score = scoreFinal)
        }
    }

    fun setGame(name: Int) {
        _uiState.update{ currentState ->
            currentState.copy(title = name)
        }
    }
}