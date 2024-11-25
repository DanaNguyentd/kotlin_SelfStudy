package com.example.pokemoncollection

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokemoncollection.ui.theme.PokemonCollectionTheme

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val jsonString = readJsonFromAssets(this, getString(R.string.pokemonlist_json))
        val pokemonList = parseJsonToModel(jsonString)
        enableEdgeToEdge()
        setContent {
            PokemonCollectionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    PokemonCollectionLayout(this, pokemonList)
                }
            }
        }
    }
}

data class Pokemon(
    val name: String,
    val img: String,
    val type: String,
    val weaknesses: String,
    val information: String
)

fun readJsonFromAssets(context: Context, fileName: String): String {
    return context.assets.open(fileName).bufferedReader().use { it.readText() }
}

fun parseJsonToModel(jsonString: String): List<Pokemon> {
    val gson = Gson()
    return gson.fromJson(jsonString, object : TypeToken<List<Pokemon>>() {}.type)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonCollectionLayout(context: Context, pokemonList: List<Pokemon>) {
    val sizeOfPokedex = pokemonList.size
    var currentStep by remember { mutableIntStateOf(0) }

    val imageid = context.resources.getIdentifier(pokemonList[currentStep].img, "drawable", context.packageName)
    val nameid = pokemonList[currentStep].name
    val typeid = pokemonList[currentStep].type
    val weaknessesid = pokemonList[currentStep].weaknesses
    val informationid = pokemonList[currentStep].information

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "POKEDEX",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .verticalScroll(rememberScrollState())
                    .safeDrawingPadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Canvas(
                        modifier = Modifier
                            .size(280.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .shadow(5.dp)
                    ) {
                        // access DrawScope here
                        drawRect(
                            color = Color.White,
                            size = size
                        )
                    }
                    // Image (A)
                    Image(
                        painter = painterResource(imageid),
                        contentDescription = "",
                        modifier = Modifier
                            .size(250.dp)
                    )
                }
                // Title (B)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(15.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    //Main Title
                    Text(
                        text = nameid,
                        fontSize = 45.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    //Sub Title 1
                    Text(
                        text = "Type: $typeid",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(modifier = Modifier
                        .padding(10.dp),
                        verticalArrangement = Arrangement.Center
                    ){
                        //Sub Title 2
                        Text(
                            text = "Weaknesses: $weaknessesid",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        //Sub Title 3
                        Text(
                            text = informationid,
                            fontSize = 15.sp,
                            fontStyle = FontStyle.Italic,
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
                // Buttons (C)
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    // Previous
                    ElevatedButton(
                        onClick = {
                            currentStep--
                            if (currentStep < 0) {currentStep = sizeOfPokedex-1}
                        },
                        shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.Blue,
                            containerColor = Color.LightGray
                        ),
                        border = BorderStroke(width = 2.dp, brush = SolidColor(Color.Black)),
                        modifier = Modifier
                            .size(width = 120.dp, height = 50.dp)
                    ){
                        Text(
                            stringResource(R.string.previousbnt),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    // Next
                    ElevatedButton(
                        onClick = {
                            currentStep++
                            if (currentStep >= sizeOfPokedex) {currentStep = 0}
                        },
                        shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.Blue,
                            containerColor = Color.LightGray
                        ),
                        border = BorderStroke(width = 2.dp, brush = SolidColor(Color.Black)),
                        modifier = Modifier.size(width = 120.dp, height = 50.dp)
                    ){
                        Text(
                            stringResource(R.string.nextbnt),
                        )
                    }

                }
            }
        }
    }

}

/*@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PokemonCollectionPreview() {
    PokemonCollectionTheme {
        val pokemons = mutableListOf(
            Pokemon("Bulbasaur", "Bulbasaur.png", "Grass, Poison", "Fire, Ice, Flying, Psychic", "For some time after its birth, it uses the nutrients that are packed into the seed on its back in order to grow."),
            Pokemon("Ivysaur", "Ivysaur.png", "Grass, Poison", "Fire, Ice, Flying, Psychic", "The more sunlight Ivysaur bathes in, the more strength wells up within it, allowing the bud on its back to grow larger.")
        )
        PokemonCollectionLayout(pokemons)
    }
}*/