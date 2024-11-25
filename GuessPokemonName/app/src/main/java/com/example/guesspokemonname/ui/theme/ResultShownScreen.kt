package com.example.guesspokemonname.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guesspokemonname.R

@Composable
fun ResultShownScreen(
    score: Int,
    startAgain: () -> Unit
){
    val mediumPadding = dimensionResource(R.dimen.padding_medium)

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
            Text(text = stringResource(R.string.congratulations))
            Text(text = stringResource(R.string.you_scored, score))
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {startAgain()}
            ){
                Text(
                    text = stringResource(R.string.play_again),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun StartOrderPreview() {
    GuessPokemonNameTheme {
        ResultShownScreen(
            score = 10,
            {}
        )
    }
}