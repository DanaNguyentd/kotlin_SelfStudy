package com.example.guesspokemonname.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guesspokemonname.R
import com.example.guesspokemonname.data.MAX_POKEMON_IN_GAME
import java.util.Locale

@Composable
fun GameRunningScreen(
    pokemonList: List<String>,
    EndGameToResult: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    val mediumPadding = dimensionResource(R.dimen.padding_medium)
    var question by remember { mutableIntStateOf(1) }
    var currentGuess by remember { mutableStateOf("") }
    var userGuessList  by remember {mutableStateOf(List(MAX_POKEMON_IN_GAME) {""}.toMutableList())}
    var isGuessWrong by remember { mutableStateOf(false) }
    var currentShuffleWord by remember { mutableStateOf(shuffleCurrentWord(pokemonList[0]))}

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .safeDrawingPadding()
            .padding(mediumPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title of the game
        Text(
            text = "Who's that pokemon?",
            style = typography.titleLarge,
        )
        // Game Layout
        GameLayout(
            question,
            pokemonList[question-1],
            currentShuffleWord,
            currentGuess,
            onUserGuessChanged = {currentGuess = it},
            isGuessWrong,
            onKeyboardDone = {isGuessWrong = checkUserGuess(currentGuess, pokemonList[question-1])},
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(mediumPadding)
        )

        // Bnt layout
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(mediumPadding),
            verticalArrangement = Arrangement.spacedBy(mediumPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    println("Dana currentGuess = " + currentGuess)
                    userGuessList[question - 1] = currentGuess
                    if (question < MAX_POKEMON_IN_GAME){
                        question ++
                        currentGuess = ""
                        isGuessWrong = false
                        currentShuffleWord = shuffleCurrentWord(pokemonList[question - 1])
                    } else {
                        EndGameToResult(getScore(pokemonList, userGuessList))
                    }
                }
            ) {
                Text(
                    text = stringResource(R.string.submit),
                    fontSize = 16.sp
                )
            }
        }

    }
}

@Composable
fun GameLayout(
    question: Int,
    pokemonName: String,
    scrambledPokemon: String,
    guessPokemon: String,
    onUserGuessChanged: (String) -> Unit,
    isGuessWrong: Boolean,
    onKeyboardDone: () -> Unit,
    modifier: Modifier = Modifier
){
    val mediumPadding = dimensionResource(R.dimen.padding_medium)
    val context = LocalContext.current
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(mediumPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(mediumPadding)
        ){
            Text(
                modifier = Modifier
                    .clip(shapes.medium)
                    .background(colorScheme.surfaceTint)
                    .padding(horizontal = 10.dp, vertical = 4.dp)
                    .align(alignment = Alignment.End),
                text = "$question/$MAX_POKEMON_IN_GAME",
                style = typography.titleMedium,
                color = colorScheme.onPrimary
            )
            Image(
                painter = painterResource(context.resources.getIdentifier(pokemonName, "drawable", context.packageName)),
                contentDescription = "",
                modifier = Modifier
                    .size(150.dp)
            )
            Text(
                text = scrambledPokemon,
                style = typography.displayMedium
            )
            Text(
                text = stringResource(R.string.instructions),
                textAlign = TextAlign.Center,
                style = typography.titleMedium
            )
            OutlinedTextField(
                value = guessPokemon,
                singleLine = true,
                shape = shapes.large,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorScheme.surface,
                    unfocusedContainerColor = colorScheme.surface,
                    disabledContainerColor = colorScheme.surface,
                ),
                onValueChange =  onUserGuessChanged,
                label = {
                    if (isGuessWrong) {
                        Text(stringResource(R.string.wrong_guess))
                    } else {
                        Text(stringResource(R.string.enter_your_word))
                    }
                },
                isError = isGuessWrong,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Send
                ),
                keyboardActions = KeyboardActions(
                    onSend = { onKeyboardDone() }
                )
            )
        }
    }
}

fun shuffleCurrentWord(word: String): String {
    val tempWord = word.toCharArray()
    // Scramble the word
    tempWord.shuffle()
    while (String(tempWord) == word) {
        tempWord.shuffle()
    }
    return String(tempWord)
}

fun checkUserGuess(userGuess: String, currentWord: String) : Boolean {
    val userGuessFixed = userGuess.lowercase(Locale.getDefault()).replace(" ", "")
    return !userGuessFixed.equals(currentWord, ignoreCase = true)
}

fun getScore(original: List<String>, guess: List<String>) : Int{
    var score = 0
    for ( i in original.indices) {
        val userGuessFixed = guess[i].lowercase(Locale.getDefault()).replace(" ", "")
        if (original[i].equals(userGuessFixed, ignoreCase = true)) {
                score ++;
            }
    }
    return score
}