package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CardDesign(
                        name = stringResource(R.string.Name_of_card),
                        title = stringResource(R.string.Title_of_person),
                        phone = stringResource(R.string.Phone_number),
                        facebook = "@GengarPokemonFb",
                        email = "Gengar@pokemon.com",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }
}

@Composable
fun CardDesign(name: String, title: String, phone: String, facebook: String, email: String, modifier: Modifier = Modifier) {
    val backgroundimage = painterResource(R.drawable.background)
    Box(modifier) {
        Image(
            painter = backgroundimage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.3F,
            modifier = Modifier.size(10000.dp)
        )
        val logoImage = painterResource(R.drawable.gengarlogo)
        val imageModifier = Modifier
            .size(165.dp)
            .clip(CircleShape)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = logoImage,
                contentDescription = null,
                modifier = imageModifier
            )
            Text(
                text = name,
                fontSize = 56.sp,
                textAlign = TextAlign.Center,
                color = Color(0xffaa00ff)
            )
            Text(
                text = title,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color(0xff9c27b0)
            )
        }

        val tickImage = painterResource(R.drawable.tick)
        val imageTickModifier = Modifier
            .size(23.dp)
            .clip(CircleShape)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(
                    bottom = 50.dp
                ),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Column(
                    modifier = Modifier.padding(bottom = 25.dp)
                ){
                    Image(
                        painter = tickImage,
                        contentDescription = null,
                        modifier = imageTickModifier
                    )
                    Image(
                        painter = tickImage,
                        contentDescription = null,
                        modifier = imageTickModifier
                    )
                    Image(
                        painter = tickImage,
                        contentDescription = null,
                        modifier = imageTickModifier
                    )
                }
                Column(
                    modifier = Modifier.padding(start = 5.dp, bottom = 25.dp)
                ){
                    Text(
                        text = phone,
                        fontSize = 16.sp,
                        color = Color(0xffaa00ff)
                    )
                    Text(
                        text = facebook,
                        fontSize = 16.sp,
                        color = Color(0xffaa00ff)
                    )
                    Text(
                        text = email,
                        fontSize = 16.sp,
                        color = Color(0xffaa00ff)
                    )
                }
            }
        }
    }

}

@Preview(
    name = "Test",
    showSystemUi = true,
    showBackground = true
)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        CardDesign("Gengar", "Pokemon Master", "+00 (00) 000 000", "@GengarPokemonFb", "Gengar@pokemon.com")
    }
}