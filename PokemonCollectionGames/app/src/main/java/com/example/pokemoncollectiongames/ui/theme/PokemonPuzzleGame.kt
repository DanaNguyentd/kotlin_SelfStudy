package com.example.pokemoncollectiongames.ui.theme

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.example.pokemoncollectiongames.R
import com.example.pokemoncollectiongames.data.Pokemon
import com.example.pokemoncollectiongames.ui.theme.pokemonPuzzleGame.GameResult
import com.example.pokemoncollectiongames.ui.theme.pokemonPuzzleGame.GameRunning
import com.example.pokemoncollectiongames.ui.theme.pokemonPuzzleGame.StartGame

/**
 * enum values that represent the screens in the app
 */
enum class PokemonPuzzleScreen(@StringRes val title: Int) {
    Start(R.string.startscreen),
    Game(R.string.gamescreen),
    Result(R.string.resultscreen),
    HighScore(R.string.highscorescreen)
}

@Composable
fun PokemonPuzzleGame(
    pokemons: List<Pokemon>,
    homepage: () -> Unit,
    viewModel: GameViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = PokemonPuzzleScreen.valueOf(
        backStackEntry?.destination?.route ?: PokemonPuzzleScreen.Start.name
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = PokemonPuzzleScreen.Start.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = PokemonPuzzleScreen.Start.name) {
                StartGame(
                    onNextButtonClicked = {
                        navController.navigate(PokemonPuzzleScreen.Game.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }

            composable(route = PokemonPuzzleScreen.Game.name) {
                GameRunning(
                    pokemons,
                    EndGameToResult = {
                        viewModel.setScore(it)
                        navController.navigate(PokemonPuzzleScreen.Result.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }

            composable(route = PokemonPuzzleScreen.Result.name) {
                GameResult(
                    uiState.score,
                    startAgain = {
                        viewModel.resetGame()
                        navController.navigate(PokemonPuzzleScreen.Start.name)
                    },
                    homepage
                )
            }
        }
    }
}