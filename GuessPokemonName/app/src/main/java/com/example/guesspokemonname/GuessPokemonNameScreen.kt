package com.example.guesspokemonname

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.guesspokemonname.ui.theme.GameRunningScreen
import com.example.guesspokemonname.ui.theme.GameViewModel
import com.example.guesspokemonname.ui.theme.StartScreen
import com.example.guesspokemonname.ui.theme.ResultShownScreen

/**
 * enum values that represent the screens in the app
 */

enum class GuessPokemonScreen(@StringRes val title: Int) {
    Start(R.string.startscreen),
    Game(R.string.gamescreen),
    Result(R.string.resultscreen),
    Highscore(R.string.highscorescreen)
}

@Composable
fun GuessPokemonNameAll(
    viewModel: GameViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = GuessPokemonScreen.valueOf(
        backStackEntry?.destination?.route ?: GuessPokemonScreen.Start.name
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = GuessPokemonScreen.Start.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ){
            composable(route = GuessPokemonScreen.Start.name) {
                StartScreen(
                    onNextButtonClicked = {
                        navController.navigate(GuessPokemonScreen.Game.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
            composable(route = GuessPokemonScreen.Game.name) {
                GameRunningScreen(
                    uiState.pokemonList,
                    EndGameToResult = {
                        viewModel.setScore(it)
                        navController.navigate(GuessPokemonScreen.Result.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
            composable(route = GuessPokemonScreen.Result.name) {
                ResultShownScreen(
                    uiState.score,
                    startAgain = {
                        viewModel.resetGame()
                        navController.navigate(GuessPokemonScreen.Start.name)
                    }
                )
            }
        }
    }
}