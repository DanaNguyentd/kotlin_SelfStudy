package com.example.pokemonregion.data

import com.example.pokemonregion.region.Region
import com.example.pokemonregion.pokemon.Pokemon
import com.example.pokemonregion.pokemon.Type
import com.example.pokemonregion.R

object DataSource {
    val Kanto = listOf(
        Pokemon("Bulbasaur", R.drawable.bulbasaur, listOf(Type.Grass, Type.Poison)),
        Pokemon("Charmander", R.drawable.charmander, listOf(Type.Fire, Type.Flying)),
        Pokemon("Squirtle", R.drawable.squirtle, listOf(Type.Water)),
        Pokemon("Gastly", R.drawable.gastly, listOf(Type.Ghost, Type.Poison)),
        Pokemon("Pichu", R.drawable.pichu, listOf(Type.Electric)),
        Pokemon("Munchlax", R.drawable.munchlax, listOf(Type.Normal))
    )

    val Johto = listOf(
        Pokemon("Cyndaquil", R.drawable.cyndaquil, listOf(Type.Fire)),
        Pokemon("Totodile", R.drawable.totodile, listOf(Type.Water)),
        Pokemon("Chikorita", R.drawable.chikorita, listOf(Type.Grass))
    )

    val Hoenn = listOf(
        Pokemon("Treecko", R.drawable.treecko, listOf(Type.Grass)),
        Pokemon("Torchic", R.drawable.torchic, listOf(Type.Fire, Type.Fighting)),
        Pokemon("Mudkip", R.drawable.mudkip, listOf(Type.Water, Type.Ground))
    )

    val Kalos = listOf(
        Pokemon("Froakie", R.drawable.froakie, listOf(Type.Water, Type.Dark)),
        Pokemon("Chespin", R.drawable.chespin, listOf(Type.Grass, Type.Fighting)),
        Pokemon("Fennekin", R.drawable.fennekin, listOf(Type.Fire, Type.Psychic)),
        Pokemon("Scatterbug", R.drawable.scatterbug, listOf(Type.Bug, Type.Flying))
    )

    val Sinnoh = listOf(
        Pokemon("Piplup", R.drawable.piplup, listOf(Type.Water, Type.Steel)),
        Pokemon("Turtwig", R.drawable.turtwig, listOf(Type.Grass, Type.Ground)),
        Pokemon("Chimchar", R.drawable.chimchar, listOf(Type.Fire, Type.Fighting)),
        Pokemon("Riolu", R.drawable.riolu, listOf(Type.Steel, Type.Fighting))

    )

    val Unova = listOf(
        Pokemon("Snivy", R.drawable.snivy, listOf(Type.Grass)),
        Pokemon("Tepig", R.drawable.tepig, listOf(Type.Fire, Type.Fighting)),
        Pokemon("Oshawott", R.drawable.oshawott, listOf(Type.Water)),
        Pokemon("Trubbish", R.drawable.trubbish, listOf(Type.Poison)),
        Pokemon("Vanillite", R.drawable.vanillite, listOf(Type.Ice))

    )

    val Alola = listOf(
        Pokemon("Rowlet", R.drawable.rowlet, listOf(Type.Grass, Type.Flying, Type.Ghost)),
        Pokemon("Litten", R.drawable.litten, listOf(Type.Fire, Type.Dark)),
        Pokemon("Popplio", R.drawable.popplio, listOf(Type.Water, Type.Fairy)),
        Pokemon("Stufful", R.drawable.stufful, listOf(Type.Normal, Type.Fighting)),
        Pokemon("PichuAlola", R.drawable.pichualola, listOf(Type.Electric,Type.Psychic))

    )

    val Galar = listOf(
        Pokemon("Sobble", R.drawable.sobble, listOf(Type.Water)),
        Pokemon("Scorbunny", R.drawable.scorbunny, listOf(Type.Fire)),
        Pokemon("Grookey", R.drawable.grookey, listOf(Type.Grass)),
        Pokemon("Farfetch", R.drawable.farfetch, listOf(Type.Flying, Type.Normal))
    )

    val regions = listOf(
        Region("KANTO", R.drawable.kanto, R.string.kantoRegion, Kanto),
        Region("JOHTO", R.drawable.johto, R.string.johtoRegion, Johto),
        Region("HOENN", R.drawable.hoenn, R.string.hoennRegion, Hoenn),
        Region("SINNOH", R.drawable.sinnoh, R.string.sinnohRegion, Sinnoh),
        Region("UNOVA", R.drawable.unova, R.string.unovaRegion, Unova),
        Region("KALOS", R.drawable.kalos, R.string.kalosRegion, Kalos),
        Region("ALOLA", R.drawable.alola, R.string.alolaRegion, Alola),
        Region("GALAR", R.drawable.galar, R.string.galarRegion, Galar)
    )
}