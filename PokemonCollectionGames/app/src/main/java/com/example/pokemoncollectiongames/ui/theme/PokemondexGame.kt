package com.example.pokemoncollectiongames.ui.theme

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pokemoncollectiongames.R

import com.example.pokemoncollectiongames.data.Pokemon
import com.example.pokemoncollectiongames.data.Type
import com.example.pokemoncollectiongames.data.TypeColor
import com.example.pokemoncollectiongames.data.printType
import com.example.pokemoncollectiongames.ui.theme.utils.NavigationType


@Composable
fun PokedexGame(
    navigationType: NavigationType,
    pokemons: List<Pokemon>,
    homepage: () -> Unit,
    navController: NavHostController = rememberNavController()
){
    val navigationItemContentList = listOf(
        NavigationItemContent(
            name = "Home",
            icon = R.drawable.homeicon,
            text = "Home"
        ),
        NavigationItemContent(
            name = "Mega",
            icon = R.drawable.megaicon,
            text = "Mega"
        ),
        NavigationItemContent(
            name = "Giga",
            icon = R.drawable.gigaicon,
            text = "Giga"
        ),
        NavigationItemContent(
            name = "Alola",
            icon = R.drawable.alolaicon,
            text = "Alola"
        ),
        NavigationItemContent(
            name = "Galar",
            icon = R.drawable.galarlogo,
            text = "Galar"
        ),
        NavigationItemContent(
            name = "Hisui",
            icon = R.drawable.hisulogo,
            text = "Hisui"
        ),

    )

    var pokemonId by remember { mutableIntStateOf(0) }
    var showPage by remember { mutableStateOf("Home") }

    Scaffold(
        topBar = {
            BackToHome(
                R.string.Pokemondex,
                navigateUp = {homepage()}
            )
        },
        bottomBar = {
            AnimatedVisibility(visible = navigationType == NavigationType.BOTTOM_NAVIGATION){
                NavigationBottom(
                    currentPage = showPage,
                    UpdateShowPage = {showPage = it},
                    navigationItemContentList = navigationItemContentList,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)){
            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .verticalScroll(rememberScrollState())
                    .safeDrawingPadding(),
                verticalArrangement = Arrangement.Center
            ){
                Row(
                    modifier = Modifier.fillMaxSize(),
                ){
                    println(navigationType)
                    AnimatedVisibility(visible = navigationType == NavigationType.PERMANENT_NAVIGATION_DRAWER){
                        NavigationDrawer(
                            currentPage = showPage,
                            UpdateShowPage = {showPage = it},
                            navigationItemContentList = navigationItemContentList,
                            modifier = Modifier
                        )
                    }
                    PokemonShow(
                        pokemons[pokemonId],
                        NextPokemon = {
                            pokemonId++
                            if (pokemonId == pokemons.size){
                                pokemonId = 0
                            }
                        },
                        PreiousPokemon = {
                            pokemonId --
                            if (pokemonId < 0 ){
                                pokemonId = pokemons.size - 1
                            }
                        },
                        showPage
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackToHome(
    @StringRes currentScreenTitle: Int,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
){
    CenterAlignedTopAppBar(
        title = { Text(stringResource(currentScreenTitle)) },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = ""
                )
            }
        }
    )
}
@Composable
private fun NavigationDrawer(
    currentPage: String,
    UpdateShowPage: (String) -> Unit,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
){
    NavigationRail(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationRailItem(
                // Display if the icon it is select or not
                selected = currentPage == navItem.name,
                // Click listener for the icon
                onClick = { UpdateShowPage(navItem.name) },
                // The icon resource
                icon = {
                    Icon(
                        painterResource(navItem.icon),
                        contentDescription = navItem.text,
                        modifier = Modifier.size(50.dp)
                    )
                },
                // Text that shows bellow the icon
                label = {
                    Text(text = navItem.name)
                }
            )
        }
    }
}

@Composable
private fun NavigationBottom(
    currentPage: String,
    UpdateShowPage: (String) -> Unit,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
){
    NavigationBar(
        modifier = modifier
    ) {
        for (navItem in navigationItemContentList) {
            NavigationBarItem(
                // Display if the icon it is select or not
                selected = currentPage == navItem.name,
                // Click listener for the icon
                onClick = { UpdateShowPage(navItem.name) },
                // The icon resource
                icon = {
                    Icon(
                        painterResource(navItem.icon),
                        contentDescription = navItem.text,
                        modifier = Modifier.size(35.dp)
                    )
                },
                // Text that shows bellow the icon
                label = {
                    Text(text = navItem.name)
                }
            )
        }
    }
}

@Composable
fun PokemonShow(
    pokemon: Pokemon,
    NextPokemon: () -> Unit,
    PreiousPokemon: () -> Unit,
    showPage: String,
) {
    val context = LocalContext.current
    if (showPage == "Home") {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Canvas(
                    modifier = Modifier
                        .size(250.dp)
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
                    painter = painterResource(context.resources.getIdentifier(pokemon.img, "drawable", context.packageName)),
                    contentDescription = "",
                    modifier = Modifier
                        .size(220.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            //Main Title
            Text(
                text = pokemon.name,
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(10.dp))
            //Type
            Column(
                modifier = Modifier.padding(start = 5.dp)
            ){
                Text(
                    text = "Types",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Blue,
                    style = MaterialTheme.typography.bodyLarge
                )
                PokemonTypeLayout(pokemon.type)
            }
            Spacer(modifier = Modifier.height(10.dp))
            //Weakness
            Column(
                modifier = Modifier.padding(start = 5.dp)
            ){
                Text(
                    text = "Weakness",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Blue,
                    style = MaterialTheme.typography.bodyLarge
                )
                PokemonTypeLayout(pokemon.weakness)
            }
            Spacer(modifier = Modifier.height(10.dp))
            //Info
            Column(
                modifier = Modifier.padding(start = 5.dp)
            ){
                Text(
                    text = "Information",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Blue,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = pokemon.information,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
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
                        PreiousPokemon()
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
                       NextPokemon()
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
    } else if (showPage == "Mega") {
        SubPage("Mega", pokemon, stringResource(R.string.Mega_Info), "This pokemon could not do mega Involution")
    } else if (showPage == "Giga") {
        SubPage("Giga", pokemon, stringResource(R.string.Giga_Info), "This pokemon doesn't have Gigamax form")
    } else if (showPage == "Alola") {
        SubRegion(pokemon.alola, "Alola", pokemon.img, stringResource(R.string.Alola_Info), "This pokemon doesn't have Alolan form")
    } else if (showPage == "Galar") {
        SubRegion(pokemon.galar, "Galar", pokemon.img, stringResource(R.string.Galar_Info), "This pokemon doesn't have Galar form")
    } else if (showPage == "Hisui") {
        SubRegion(pokemon.hisui, "Hisui", pokemon.img, stringResource(R.string.Hisui_Info), "This pokemon doesn't have Hisui form")
    }
}

@Composable
fun SubRegion(
    type: List<Type>,
    nameofSubpage: String,
    pokemonimg: String,
    info: String,
    subText: String
) {
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        //Info
        Box(
            modifier = Modifier
                .padding(5.dp)
                .border(2.dp, Color.Black, shape = RoundedCornerShape(16.dp))
        ) {
            Text(
                text = info,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Blue,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(5.dp)
            )
        }
        if(type.size>0) {
            Image(
                painter = painterResource(context.resources.getIdentifier(pokemonimg + "_" + nameofSubpage.lowercase(), "drawable", context.packageName)),
                contentDescription = "",
                modifier = Modifier
                    .size(250.dp)
            )
            Text(
                text = "Types",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Blue,
                style = MaterialTheme.typography.bodyLarge
            )
            PokemonTypeLayout(type)
        } else {
            // Image (A)
            Image(
                painter = painterResource(R.drawable.notav),
                contentDescription = "",
            )
            //Info
            Text(
                text = subText,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red,
                style = MaterialTheme.typography.bodyLarge
            )
        }

    }
}
@Composable
fun SubPage(
    nameofSubpage: String,
    pokemon: Pokemon,
    Info: String,
    subText: String
){
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        //Info
        Box(
            modifier = Modifier
                .padding(5.dp)
                .border(2.dp, Color.Black, shape = RoundedCornerShape(16.dp))
        ) {
            Text(
                text = Info,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Blue,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(5.dp)
            )
        }
        if (pokemon.mega) {
            // Image (A)
            Image(
                painter = painterResource(context.resources.getIdentifier(pokemon.img + "_" + nameofSubpage.lowercase(), "drawable", context.packageName)),
                contentDescription = "",
                modifier = Modifier
                    .size(250.dp)
            )
            Text(
                text = nameofSubpage + " " + pokemon.name,
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red,
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            // Image (A)
            Image(
                painter = painterResource(R.drawable.notav),
                contentDescription = "",
            )
            //Info
            Text(
                text = subText,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun PokemonTypeLayout(types: List<Type>) {
    val sizeofType = types.size ?: 0
    LazyVerticalGrid(
        columns = GridCells.Fixed(sizeofType),
        modifier = Modifier
            .height(40.dp)
            .padding(5.dp)
    ) {
        items(types) { type ->
            PokemonType(type)
        }
    }
}

@Composable
fun PokemonType(type: Type){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(30.dp)
            .background(TypeColor(type))
    ){
        Text(
            text = printType(type),
            style = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(2.dp)
        )
    }
}

private data class NavigationItemContent(
    val name: String,
    @DrawableRes val icon: Int,
    val text: String
)