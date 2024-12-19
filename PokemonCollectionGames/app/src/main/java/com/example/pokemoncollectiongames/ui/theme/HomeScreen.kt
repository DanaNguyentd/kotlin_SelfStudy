package com.example.pokemoncollectiongames.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokemoncollectiongames.R

@Composable
fun HomeScreen(
    puzzleGame: () -> Unit,
    wordGame: () -> Unit,
    pokemonDex: () -> Unit,
    pokemonRegion: () -> Unit ,
    pokemonCard: () -> Unit,
    modifier: Modifier = Modifier
) {
    val offset = Offset(5.0f, 10.0f)
    Column(
        modifier = Modifier
            .background(Color(0xFFE0F2F1))
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Title
        Text(
            text = "Pokemon Games",
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                shadow = Shadow(
                    color = Color.LightGray,
                    offset = offset,
                    blurRadius = 3f
                )
            ),
            modifier = Modifier.padding(10.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        LazyColumn(
            modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                GameButton(R.string.PokemonPuzzle, puzzleGame, R.drawable.pokemonpuzzle)
                Spacer(modifier = Modifier.height(15.dp))
                GameButton(R.string.PokemonGuessName, wordGame, R.drawable.pokemonnameguess)
                Spacer(modifier = Modifier.height(15.dp))
                GameButton(R.string.Pokemondex, pokemonDex, R.drawable.pokedex)
                Spacer(modifier = Modifier.height(15.dp))
                GameButton(R.string.PokemonReg, pokemonRegion, R.drawable.pokemonregion)
                Spacer(modifier = Modifier.height(15.dp))
                //GameButton(R.string.PokemonCard, pokemonCard,0)
            }
        }
    }
}

@Composable
fun GameButton(
    nameofGame: Int,
    game: () -> Unit,
    img: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text =  stringResource(nameofGame),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(2.dp))
        Button(
            onClick = game,
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ){
            Image(
                painter = painterResource(img),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
            )
        }
    }
    Spacer(modifier = Modifier.height(10.dp))

}
