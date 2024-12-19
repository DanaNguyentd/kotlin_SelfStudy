package com.example.pokemoncollectiongames.ui.theme

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokemoncollectiongames.R
import com.example.pokemoncollectiongames.data.Pokemon
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import com.example.pokemoncollectiongames.ui.theme.utils.NavigationType

/**
 * enum values that represent the screens in the app
 */
enum class SubApp(@StringRes val title: Int) {
    Home(R.string.Home),
    PokemonPuzzle(R.string.PokemonPuzzle),
    PokemonGuessName(R.string.PokemonGuessName),
    Pokemondex(R.string.Pokemondex),
    PokemonRegion(R.string.PokemonReg),
    PokemonCard(R.string.PokemonCard)
}

@Composable
fun MainTheme(
    pokemons: List<Pokemon>,
    windowSize: WindowWidthSizeClass,
    viewModel: GameViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = SubApp.valueOf(
        backStackEntry?.destination?.route ?: SubApp.Home.name
    )

    val navigationType: NavigationType = when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            NavigationType.BOTTOM_NAVIGATION
        }
        WindowWidthSizeClass.Medium -> {
            NavigationType.NAVIGATION_RAIL
        }
        WindowWidthSizeClass.Expanded -> {
            NavigationType.PERMANENT_NAVIGATION_DRAWER
        }
        else -> {
            NavigationType.BOTTOM_NAVIGATION
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SubApp.Home.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = SubApp.Home.name) {
                HomeScreen(
                    puzzleGame = {
                        navController.navigate(SubApp.PokemonPuzzle.name)
                    },
                    wordGame = {
                        navController.navigate(SubApp.PokemonGuessName.name)
                    },
                    pokemonDex = {
                        navController.navigate(SubApp.Pokemondex.name)
                    },
                    pokemonRegion = {
                        navController.navigate(SubApp.PokemonRegion.name)
                    },
                    pokemonCard = {
                        navController.navigate(SubApp.PokemonCard.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }

            // Puzzle game
            composable(route = SubApp.PokemonPuzzle.name) {
                PokemonPuzzleGame(
                    pokemons,
                    homepage = {
                        navController.navigate(SubApp.Home.name)
                    }
                )
            }

            // Name game
            composable(route = SubApp.PokemonGuessName.name) {
                PokemonGuessNameGame(
                    pokemons,
                    homepage = {
                        navController.navigate(SubApp.Home.name)
                    }
                )
            }

            // Pokedex
            composable(route = SubApp.Pokemondex.name) {
                PokedexGame(
                    navigationType,
                    pokemons,
                    homepage = {
                        navController.navigate(SubApp.Home.name)
                    }
                )
            }

            // Pokemon region
            composable(route = SubApp.PokemonRegion.name) {
                PokemonRegion(
                    navigationType,
                    pokemons,
                    homepage = {
                        navController.navigate(SubApp.Home.name)
                    }
                )
            }
        }
    }
}