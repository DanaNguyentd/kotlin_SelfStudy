package com.example.pokemoncollectiongames.ui.theme.pokemonGuessNameGame

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pokemoncollectiongames.R

@Composable
fun StartGame(
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = onNextButtonClicked,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ){
            Image(
                painter = painterResource(R.drawable.startimg),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(250.dp)
            )
        }
    }
}