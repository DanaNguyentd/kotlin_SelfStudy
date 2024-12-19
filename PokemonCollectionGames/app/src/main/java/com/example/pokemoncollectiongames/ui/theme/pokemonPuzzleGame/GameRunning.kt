package com.example.pokemoncollectiongames.ui.theme.pokemonPuzzleGame

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokemoncollectiongames.R
import com.example.pokemoncollectiongames.data.MAX_OF_GUESS
import com.example.pokemoncollectiongames.data.Pokemon
import com.example.pokemoncollectiongames.data.TOTAL_OF_QUESTION
import com.example.pokemoncollectiongames.data.pickRandomAndShuffle

@Composable
fun GameRunning(
    pokemons: List<Pokemon>,
    EndGameToResult: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val mediumPadding = dimensionResource(R.dimen.padding_medium)

    val questionList by remember { mutableStateOf(pickRandomAndShuffle(pokemons))}
    var question by remember { mutableIntStateOf(1) }
    var currentGuess by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var hidden by remember { mutableStateOf(true) }
    var currentShuffle by remember { mutableStateOf(shuffleImgPuzzle())}

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
            pokemons,
            questionList[question - 1],
            question,
            currentGuess,
            currentShuffle,
            score,
            hidden,
            showResultImg = { hidden = false},
            nextQuestion = {
                question++
                currentGuess = 0
                currentShuffle = shuffleImgPuzzle()
                hidden = true
            },
            updateScore = {score++},
            updateCurrentGuess = {currentGuess++},
            endGame = {EndGameToResult(score)}
            )
    }
}

@Composable
fun GameLayout(
    pokemons: List<Pokemon>,
    pokemon: Pokemon,
    question: Int,
    currentGuess: Int,
    currentShuffle: List<Int>,
    score: Int,
    hidden: Boolean,
    showResultImg: () -> Unit,
    nextQuestion: () -> Unit,
    updateScore: () -> Unit,
    endGame: () -> Unit,
    updateCurrentGuess : () -> Unit,
){
    val context = LocalContext.current
    Column(
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
        ){
            Column(){
                Text(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    text = "Score",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    modifier = Modifier
                        .clip(shapes.medium)
                        .background(colorScheme.surfaceTint)
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    text = "$score/$TOTAL_OF_QUESTION",
                    style = typography.titleMedium,
                    color = colorScheme.onPrimary
                )
            }

            Column(){
                Text(
                    text = "Question",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    modifier = Modifier
                        .clip(shapes.medium)
                        .background(colorScheme.surfaceTint)
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    text = "$question/$TOTAL_OF_QUESTION",
                    style = typography.titleMedium,
                    color = colorScheme.onPrimary
                )
            }
        }

        Spacer(modifier = Modifier.height(5.dp))

        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ){
            if (hidden) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.height(400.dp)
                ) {
                    items(currentShuffle) { pokemonIndex ->
                        PokemonPicPuzzle(pokemonIndex, pokemon)
                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.height(100.dp)
                ) {
                    items(currentShuffle) { pokemonIndex ->
                        PokemonAnswerBnt(
                            pokemonIndex - 1,
                            pokemon,
                            pokemons,
                            currentGuess,
                            updateScore,
                            updateCurrentGuess,
                            showResultImg
                        )
                    }
                }
            } else { // showing the result image when answer correct
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(context.resources.getIdentifier(pokemon.img, "drawable", context.packageName)),
                        contentDescription = null,
                        )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = pokemon.name,
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Red
                        ),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    ElevatedButton(
                        onClick = {
                            if (question < TOTAL_OF_QUESTION) {
                                nextQuestion()
                            } else {
                                endGame()
                            }
                        },
                        shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.Blue,
                            containerColor = Color.LightGray
                        ),
                        border = BorderStroke(width = 2.dp, brush = SolidColor(Color.Black)),
                        modifier = Modifier.size(width = 150.dp, height = 50.dp)
                    ){
                        if (question < TOTAL_OF_QUESTION) {
                            Text("Next Question")
                        } else {
                            Text("Result")
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    if (question < TOTAL_OF_QUESTION) {
                        ElevatedButton(
                            onClick = { endGame() },
                            shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.Red,
                                containerColor = Color.LightGray
                            ),
                            border = BorderStroke(width = 2.dp, brush = SolidColor(Color.Black)),
                            modifier = Modifier.size(width = 150.dp, height = 50.dp)
                        ) {
                            Text("End Game")
                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    }
}

@Composable
fun PokemonAnswerBnt(
    index: Int,
    pokemon: Pokemon,
    pokemons: List<Pokemon>,
    currentGuess: Int,
    updateScore: () -> Unit,
    updateCurrentGuess: () -> Unit,
    showResultImg: () -> Unit
){
    var pokemonId = pokemon.id + index
    if ( pokemonId >= pokemons.size) {
        pokemonId = pokemon.id - index
    }
    var isEnabled by remember { mutableStateOf(true) }
    Button(
        onClick = {
            if (pokemon.id == pokemonId) {
                showResultImg()
                updateScore()
            } else {
                updateCurrentGuess()
                if (currentGuess == (MAX_OF_GUESS - 1)) {
                    showResultImg()
                }
            }
            isEnabled = false
        },
//        enabled = isEnabled,
        shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
        border = BorderStroke(width = 2.dp, brush = SolidColor(Color.Black)),
        modifier = Modifier
            .padding(1.dp),
        colors = ButtonDefaults.buttonColors(containerColor = if (isEnabled) Color(0xFFBBDEFB) else Color.Black)
    ){
        Text(
            text = pokemons[pokemonId - 1].name, // pokemon Id starts from 1 but array starts from 0
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color =  if (isEnabled) Color(0xFFC62828) else Color.Black // Red800
            )
        )
    }
}

@Composable
fun PokemonPicPuzzle(
    index: Int,
    pokemon: Pokemon
) {
    val context = LocalContext.current
    //println(pokemon.img)
    val imageId = context.resources.getIdentifier(pokemon.img + "_00" + index.toString(), "drawable", context.packageName)
    Image(
        painter = painterResource(id = imageId),
        contentDescription = null,
        modifier = Modifier
            .padding(2.dp)
            .border(BorderStroke(1.dp, Color.Black))
            .aspectRatio(1.0f)
    )
}

private fun shuffleImgPuzzle(): List<Int> {
    return (1..4).toList().shuffled()
}