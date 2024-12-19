package com.example.pokemoncollectiongames.ui.theme.pokemonGuessNameGame

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokemoncollectiongames.R

@Composable
fun GameResult(
    score: Int,
    startAgain: () -> Unit,
    returnHomepage: () -> Unit
){
    val mediumPadding = dimensionResource(R.dimen.padding_medium)
    val rainbowColors = listOf(
        Color.Red,
        Color.Magenta,
        Color.Blue,
        Color.Cyan,
        Color.Green,
        Color.Yellow,
        Color.Red
    )
    val scoreImg = when (score) {
        0 -> R.drawable.score0
        1 -> R.drawable.score1
        2 -> R.drawable.score2
        3 -> R.drawable.score3
        4 -> R.drawable.score4
        5 -> R.drawable.score5
        6 -> R.drawable.score6
        7 -> R.drawable.score7
        8 -> R.drawable.score8
        9 -> R.drawable.score9
        else -> R.drawable.score10
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(mediumPadding),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        border = BorderStroke(1.dp, Color.Black),
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(mediumPadding),
            verticalArrangement = Arrangement.spacedBy(mediumPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = stringResource(R.string.congratulations),
                style = TextStyle(
                    fontSize = 29.sp,
                    fontWeight = FontWeight.Normal,
                    brush = Brush.linearGradient(
                        colors = rainbowColors
                    )
                )
            )

            Spacer(modifier = Modifier.height(5.dp))

            Image(
                painter = painterResource(scoreImg),
                contentDescription = "",
                modifier = Modifier
                    .size(250.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {startAgain()},
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray
                ),
                border = BorderStroke(width = 2.dp, brush = SolidColor(Color.Black)),
            ){
                Text(
                    text = stringResource(R.string.play_again),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Blue
                    )
                )
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {returnHomepage()},
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray
                ),
                border = BorderStroke(width = 2.dp, brush = SolidColor(Color.Black)),
            ){
                Text(
                    text = "Play Other Games",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Red
                    )
                )
            }
        }
    }
}