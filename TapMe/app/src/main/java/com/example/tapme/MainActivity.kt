package com.example.tapme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.tapme.ui.theme.TapMeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            TapMeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    TapMeApp()
                }
            }
        }
    }
}

open class ComponentActivity {

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true,
    showSystemUi = true)
@Composable
fun TapMeApp() {
    var currentStep by remember { mutableIntStateOf(0) }
    var tapCount by remember { mutableIntStateOf(0) }

    val imageShowing = when (currentStep) {
        0 -> R.drawable.start
        1 -> R.drawable.face_0
        2 -> R.drawable.face_1
        3 -> R.drawable.face_2
        4 -> R.drawable.face_3
        else -> R.drawable.face_4
    }
    val textShowing = when (currentStep) {
        0 -> stringResource(R.string.start_game)
        1 -> stringResource(R.string.step_1)
        2 -> stringResource(R.string.step_2)
        3 -> stringResource(R.string.step_3)
        4 -> stringResource(R.string.happy_end)
        else -> stringResource(R.string.angry_end)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Tap and Massage Game",
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
            when (currentStep) {
                0 -> {
                    TapWithChangeImage(
                        imageShowing,
                        textShowing,
                        onImageClick = {
                            currentStep = 1
                        }
                    )
                }
                1 -> {
                    TapWithChangeImage(
                        imageShowing,
                        textShowing,
                        onImageClick = {
                            tapCount = (2..4).random()
                            currentStep++
                        }
                    )
                }
                2 -> {
                    TapWithChangeImage(
                        imageShowing,
                        textShowing,
                        onImageClick = {
                            tapCount--
                            if (tapCount == 0) {
                                currentStep++
                            }
                        }
                    )
                }
                3 -> {
                    TapWithChangeImage(
                        imageShowing,
                        textShowing,
                        onImageClick = {
                            currentStep += (0..2).random()
                        }
                    )
                }
                4, 5 -> {
                    TapWithChangeImage(
                        imageShowing,
                        textShowing,
                        onImageClick = {
                            currentStep = 0
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TapWithChangeImage(    imageResourceId: Int,
                           textResourceId: String,
                           onImageClick: () -> Unit,
                           modifier: Modifier = Modifier
                               .fillMaxSize()
                               .wrapContentSize(Alignment.Center)
) {
    val imageModifier = Modifier
        .size(250.dp)
        .clip(CircleShape)
        /*.border(
            BorderStroke(1.dp, Color.Yellow),
            CircleShape
        )*/
        .clip(CircleShape)

    Box(
        modifier = modifier
    ){
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Image(
                    painter = painterResource(imageResourceId),
                    contentDescription = textResourceId,
                    modifier = imageModifier
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = textResourceId,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
    }