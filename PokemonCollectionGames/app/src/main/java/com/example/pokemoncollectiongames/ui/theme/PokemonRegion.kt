package com.example.pokemoncollectiongames.ui.theme

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pokemoncollectiongames.ui.theme.utils.NavigationType

import com.example.pokemoncollectiongames.R
import com.example.pokemoncollectiongames.data.Pokemon
import com.example.pokemoncollectiongames.data.PokemonSeries
import com.example.pokemoncollectiongames.data.Region
import com.example.pokemoncollectiongames.data.Regions.regions
import com.example.pokemoncollectiongames.data.Type
import com.example.pokemoncollectiongames.data.TypeColor
import com.example.pokemoncollectiongames.data.printType


@Composable
fun PokemonRegion(
    navigationType: NavigationType,
    pokemons: List<Pokemon>,
    homepage: () -> Unit,
    navController: NavHostController = rememberNavController()
){
    Scaffold(
        topBar = {
            BackHome(
                R.string.PokemonReg,
                navigateUp = {homepage()}
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            val offset = Offset(5.0f, 10.0f)
            Column(
                modifier = Modifier
                .background(Color(0xFFE0F2F1))
            ){
                //Title
                Text(
                    text = "Pokemon Regions",
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
                GridPokemonRegLayout()
            }
        }
    }
}

@Composable
fun GridPokemonRegLayout() {
    LazyColumn(
        modifier = Modifier
            .padding(10.dp)
    ) {
        items(regions) { region ->
            RegionCard(
                region,
                modifier = Modifier.padding(8.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackHome(
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
fun RegionCard(region: Region, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    Card(modifier = modifier) {
        Column(){
            // Name of region
            Text(
                text = region.nameReg,
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A237E)
                ),
                modifier = Modifier.padding(10.dp)
            )
            // Image of region
            Image(
                painter = painterResource(id = region.imageReg),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp)),
            )
            // Text for each region
            Text(
                text = stringResource(id = region.detailReg),
                style = TextStyle(
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .padding(5.dp)
            )

            // Title for list of pokemon
            Text(
                text = " Popular pokemons in region",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Red
                ),
                modifier = Modifier
                    .padding(5.dp)
            )

            // Expand button
            ExpandItemButton(
                expanded = expanded,
                onClick = { expanded = !expanded },
            )
            // Pokemon in each region ( only open when do expand)
            if (expanded) {
                PokemonRegion(region.pokemonRegList)
            }
        }
    }
}

@Composable
private fun ExpandItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun PokemonRegion(pokemonList: List<PokemonSeries>?) {
    pokemonList?.let{
        PokemonButtonLayout(pokemonList)
    }  ?: run {
        Text(
            text = "UPDATING!!!!!!"
        )
    }
}

@Composable
fun PokemonButtonLayout(pokemonList: List<PokemonSeries>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.height(100.dp)
    ) {
        items(pokemonList) { pokemon ->
            PokemonBnt(pokemon)
        }
    }
}

@Composable
fun PokemonBnt(pokemon: PokemonSeries) {
    var showPopup by rememberSaveable { mutableStateOf(false) }
    Button(
        onClick = {showPopup = true},
        shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
        border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Black)),
        modifier = Modifier
            .size(width = 50.dp, height = 50.dp)
            .padding(5.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Text (
            text = pokemon.name,
            style = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Blue
            )
        )
    }
    PopupBox(showPopup = showPopup, onClickOutside = {showPopup = false}, pokemon)
}

@Composable
fun PopupBox(showPopup: Boolean, onClickOutside: () -> Unit, pokemon: PokemonSeries) {
    if (showPopup) {
        // full screen background
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green)
                .zIndex(10F),
            contentAlignment = Alignment.Center
        ) {
            // popup
            Popup(
                alignment = Alignment.Center,
                properties = PopupProperties(
                    excludeFromSystemGesture = true,
                ),
                // to dismiss on click outside
                onDismissRequest = { onClickOutside() }
            ) {
                Column() {
                    Image(
                        painter = painterResource(id = pokemon.img),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    PokemonTypeRow(pokemon.type)
                }
            }
        }
    }
}

@Composable
fun PokemonTypeRow(types: List<Type>) {
    val sizeofType = types?.size ?: 0
    LazyVerticalGrid(
        columns = GridCells.Fixed(sizeofType),
        modifier = Modifier.height(30.dp)
    ) {
        items(types) { type ->
            PokemonSeriesType(type)
        }
    }
}

@Composable
fun PokemonSeriesType(type: Type){
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