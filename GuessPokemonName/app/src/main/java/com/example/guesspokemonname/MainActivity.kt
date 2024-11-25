package com.example.guesspokemonname

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.guesspokemonname.ui.theme.GuessPokemonNameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuessPokemonNameTheme {
                GuessPokemonNameAll()
            }
        }
    }
}
