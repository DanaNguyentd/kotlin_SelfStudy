package com.example.pokemoncollectiongames.ui.theme.pokemonGuessNameGame

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
) {
    val mediumPadding = dimensionResource(R.dimen.padding_medium)

    val questionList by remember { mutableStateOf(pickRandomAndShuffle(pokemons)) }
    var question by remember { mutableIntStateOf(1) }
    var score by remember { mutableIntStateOf(0) }
    var hidden by remember { mutableStateOf(true) }
    var currentAnswer by remember {mutableStateOf((" ".repeat(questionList[question - 1].name.length)))}
    var currentShuffleAnswer by remember {mutableStateOf(shuffleString(questionList[question - 1].name, getRandomString(4)))}
    var currentGuess by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .safeDrawingPadding()
            .padding(mediumPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title of the game
        Text(
            text = "Who's that pokemon's name?",
            style = typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(10.dp))
        // Game Layout
        GameLayout(
            questionList[question - 1],
            question,
            score,
            hidden,
            currentAnswer,
            currentShuffleAnswer,
            currentGuess,
            updateCurrentAnswer = {currentAnswer = it},
            showResultImg = { hidden = false},
            nextQuestion = {
                question ++
                hidden = true
                currentAnswer = (" ".repeat(questionList[question - 1].name.length))
                currentShuffleAnswer = shuffleString(questionList[question - 1].name, getRandomString(4))
                currentGuess = 0
            },
            updateCurrentGuess = {currentGuess++},
            updateScore = {score++},
            endGame = {EndGameToResult(score)}
        )
    }
}

@Composable
fun GameLayout(
    pokemon: Pokemon,
    question: Int,
    score: Int,
    hidden: Boolean,
    currentAnswer: String,
    currentShuffleAnswer: List<Char>,
    currentGuess: Int,
    updateCurrentAnswer: (String) -> Unit,
    showResultImg: () -> Unit,
    nextQuestion: () -> Unit,
    updateCurrentGuess : () -> Unit,
    updateScore: () -> Unit,
    endGame: () -> Unit,
){
    val context = LocalContext.current
    var elementInEachRow: Int = 0
    var row: Int = 0
    if (currentShuffleAnswer.size < 13) {
        elementInEachRow = currentShuffleAnswer.size/2
        row = 2
    } else {
        elementInEachRow = currentShuffleAnswer.size/3
        row = 3
    }
    println(currentShuffleAnswer + elementInEachRow)
    Column(){
        //First row: Showing score and question order
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

        //Second row show pokemon image
        Spacer(modifier = Modifier.height(5.dp))

        if (hidden) { // show the question
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF9FBE7),
                )
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(context.resources.getIdentifier(pokemon.img, "drawable", context.packageName)),
                        contentDescription = null
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(pokemon.name.length),
                modifier = Modifier.height(40.dp),
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                items(currentAnswer.length) { index ->
                    ShowAnswer((currentAnswer[index]).toString())
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                for (i in 0..2){
                    Text(
                        text = "   X   ", // pokemon Id starts from 1 but array starts from 0
                        style = TextStyle(
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            color =  if (i<currentGuess) Color(0xFFC62828) else Color(0xFFBDBDBD) //Gray400
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                for (rowIndex in 1..row ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        var tmpEnd = 0
                        if (rowIndex == row) {
                            tmpEnd = currentShuffleAnswer.size
                        } else {
                            tmpEnd = elementInEachRow*rowIndex
                        }
                        for (letter in currentShuffleAnswer.subList(elementInEachRow*(rowIndex-1), tmpEnd)) {
                            var isEnabled by remember { mutableStateOf(true) }
                            Button(
                                onClick = {
                                    var tmp = StringBuilder(currentAnswer)
                                    var flag = false
                                    for (i in 0..<pokemon.name.length) {
                                        if (pokemon.name[i].lowercaseChar() == letter && currentAnswer[i] == ' ') {
                                            tmp.setCharAt(i, letter)
                                            updateCurrentAnswer(tmp.toString())
                                            flag = true
                                            break
                                        }
                                    }
                                    if (flag == false) {
                                        updateCurrentGuess()
                                    }
                                    isEnabled = false
                                    if(!tmp.toString().contains(' ') || currentGuess == MAX_OF_GUESS) {
                                        updateScore()
                                        showResultImg()
                                    }
                                },
                                enabled = isEnabled,
                                modifier = Modifier
                                    .padding(1.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = if (isEnabled) Color(0xFFBBDEFB) else Color.Black)
                            ){
                                Text(
                                    text = letter.toString().uppercase(), // pokemon Id starts from 1 but array starts from 0
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color =  if (isEnabled) Color(0xFFC62828) else Color.Black // Red800
                                    )
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                }
            }
            Spacer(modifier = Modifier.height(3.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Button(
                    onClick = {showResultImg()}
                ){
                    Text(
                        text = "SKIP", // pokemon Id starts from 1 but array starts from 0
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    )
                }
            }
        } else { // show the result
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

@Composable
fun ShowAnswer(
    letter: String
) {
    println(letter)
    Box(
        modifier = Modifier
            .defaultMinSize(minWidth = 30.dp, minHeight = 30.dp)
            .border(2.dp, Color.Blue, shape = RoundedCornerShape(5.dp)),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = letter.uppercase(),
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
    }
}

private fun shuffleString(str1: String, str2: String): List<Char> {
    var str = (str1.lowercase()  + str2).toCharArray().toList().shuffled();
     return str
}

private fun getRandomString(length: Int) : String {
    val allowedChars = ('a'..'z')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}