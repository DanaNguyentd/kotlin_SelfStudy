package com.example.pokemoncollectiongames.data

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

val TOTAL_OF_QUESTION = 10
val MAX_OF_GUESS = 3

enum class Type {
    @SerializedName("Grass")
    Grass,
    @SerializedName("Poison")
    Poison,
    @SerializedName("Flying")
    Flying,
    @SerializedName("Fire")
    Fire,
    @SerializedName("Water")
    Water,
    @SerializedName("Ghost")
    Ghost,
    @SerializedName("Dark")
    Dark,
    @SerializedName("Fairy")
    Fairy,
    @SerializedName("Fighting")
    Fighting,
    @SerializedName("Psychic")
    Psychic,
    @SerializedName("Steel")
    Steel,
    @SerializedName("Ground")
    Ground,
    @SerializedName("Normal")
    Normal,
    @SerializedName("Ice")
    Ice,
    @SerializedName("Electric")
    Electric,
    @SerializedName("Bug")
    Bug,
    @SerializedName("Rock")
    Rock
}

data class Pokemon(
    val id: Int,
    val name: String,
    val img: String,
    @SerializedName("type") val type: List<Type>,
    val mega: Boolean,
    val giga: Boolean,
    @SerializedName("weaknesses") val weakness: List<Type>,
    val nextInvolve: String,
    val information: String,
    @SerializedName("Alola") val alola: List<Type>,
    @SerializedName("Galar") val galar: List<Type>,
    @SerializedName("Hisui") val hisui: List<Type>
)

fun readJsonFromAssets(context: Context, fileName: String): String {
    return context.assets.open(fileName).bufferedReader().use { it.readText() }
}

fun parseJsonToModel(jsonString: String): List<Pokemon> {
    val gson = Gson()
    return gson.fromJson(jsonString, object : TypeToken<List<Pokemon>>() {}.type)
}

fun printType(type: Type): String {
    when(type) {
        Type.Grass -> return "Grass"
        Type.Poison -> return "Poison"
        Type.Flying -> return "Flying"
        Type.Fire -> return "Fire"
        Type.Water -> return "Water"
        Type.Ghost -> return "Ghost"
        Type.Dark -> return "Dark"
        Type.Fairy -> return "Fairy"
        Type.Fighting -> return "Fighting"
        Type.Psychic -> return "Psychic"
        Type.Steel -> return "Steel"
        Type.Ground -> return "Ground"
        Type.Normal -> return "Normal"
        Type.Ice -> return "Ice"
        Type.Electric -> return "Electric"
        Type.Bug -> return "Bug"
        Type.Rock -> return "Rock"
    }
}

fun TypeColor(type: Type) : Color {
    when(type) {
        Type.Grass -> return Color(0xFF66BB6A)  //Green400
        Type.Poison -> return Color(0xFF000000) //Black
        Type.Flying -> return Color(0xFF81D4FA) // LightBlue200
        Type.Fire -> return Color(0xFFF57C00) // Orange700
        Type.Water -> return Color(0xFF64B5F6) // Blue300
        Type.Ghost -> return Color(0xFF7B1FA2) //  Purple700
        Type.Dark -> return Color(0xFF424242) // Gray800
        Type.Fairy -> return Color(0xFFF48FB1) // Pink200
        Type.Fighting -> return Color(0xFFD32F2F) // Red700
        Type.Psychic -> return Color(0xFF880E4F) // Pink900
        Type.Steel -> return Color(0xFF9E9E9E) // Gray500
        Type.Ground -> return Color(0xFFFFE0B2) // Orange100
        Type.Normal -> return Color(0xFFB0BEC5) //BlueGray200
        Type.Ice ->  return Color(0xFF00B8D4) // CyanA700
        Type.Electric -> return Color(0xFFFFEB3B) // Yellow500
        Type.Bug -> return Color(0xFF009688)  //Teal500
        Type.Rock -> return Color(0xFF5D4037)  // Brown700
    }
}

fun pickRandomAndShuffle(pokemons: List<Pokemon>): List<Pokemon> {
    val pokemonList = (1..pokemons.size).toList().shuffled().subList(0, TOTAL_OF_QUESTION)
    val pokemonArray : MutableList<Pokemon> = mutableListOf()
    for (pokemonId in pokemonList) {
        pokemonArray.add(pokemons[pokemonId-1])
    }
    return pokemonArray
}