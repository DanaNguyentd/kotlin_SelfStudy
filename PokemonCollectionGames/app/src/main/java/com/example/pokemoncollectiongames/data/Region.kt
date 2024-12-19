package com.example.pokemoncollectiongames.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.pokemoncollectiongames.R

data class Region(
    val nameReg: String,
    @DrawableRes val imageReg: Int,
    @StringRes val detailReg: Int,
    val pokemonRegList: List<PokemonSeries>?
)

data class PokemonSeries(
    val name: String,
    @DrawableRes val img: Int,
    val type: List<Type>
)

object Regions {
    val Kanto = listOf(
        PokemonSeries("Bulbasaur", R.drawable.bulbasaurs, listOf(Type.Grass, Type.Poison)),
        PokemonSeries("Charmander", R.drawable.charmanders, listOf(Type.Fire, Type.Flying)),
        PokemonSeries("Squirtle", R.drawable.squirtles, listOf(Type.Water)),
        PokemonSeries("Gastly", R.drawable.gastlys, listOf(Type.Ghost, Type.Poison)),
        PokemonSeries("Pichu", R.drawable.pichus, listOf(Type.Electric)),
        PokemonSeries("Munchlax", R.drawable.munchlaxs, listOf(Type.Normal))
    )

    val Johto = listOf(
        PokemonSeries("Cyndaquil", R.drawable.cyndaquils, listOf(Type.Fire)),
        PokemonSeries("Totodile", R.drawable.totodiles, listOf(Type.Water)),
        PokemonSeries("Chikorita", R.drawable.chikoritas, listOf(Type.Grass))
    )

    val Hoenn = listOf(
        PokemonSeries("Treecko", R.drawable.treeckos, listOf(Type.Grass)),
        PokemonSeries("Torchic", R.drawable.torchics, listOf(Type.Fire, Type.Fighting)),
        PokemonSeries("Mudkip", R.drawable.mudkips, listOf(Type.Water, Type.Ground))
    )

    val Kalos = listOf(
        PokemonSeries("Froakie", R.drawable.froakies, listOf(Type.Water, Type.Dark)),
        PokemonSeries("Chespin", R.drawable.chespins, listOf(Type.Grass, Type.Fighting)),
        PokemonSeries("Fennekin", R.drawable.fennekins, listOf(Type.Fire, Type.Psychic)),
        PokemonSeries("Scatterbug", R.drawable.scatterbugs, listOf(Type.Bug, Type.Flying))
    )

    val Sinnoh = listOf(
        PokemonSeries("Piplup", R.drawable.piplups, listOf(Type.Water, Type.Steel)),
        PokemonSeries("Turtwig", R.drawable.turtwigs, listOf(Type.Grass, Type.Ground)),
        PokemonSeries("Chimchar", R.drawable.chimchars, listOf(Type.Fire, Type.Fighting)),
        PokemonSeries("Riolu", R.drawable.riolus, listOf(Type.Steel, Type.Fighting))

    )

    val Unova = listOf(
        PokemonSeries("Snivy", R.drawable.snivys, listOf(Type.Grass)),
        PokemonSeries("Tepig", R.drawable.tepigs, listOf(Type.Fire, Type.Fighting)),
        PokemonSeries("Oshawott", R.drawable.oshawotts, listOf(Type.Water)),
        PokemonSeries("Trubbish", R.drawable.trubbishs, listOf(Type.Poison)),
        PokemonSeries("Vanillite", R.drawable.vanillites, listOf(Type.Ice))

    )

    val Alola = listOf(
        PokemonSeries("Rowlet", R.drawable.rowlets, listOf(Type.Grass, Type.Flying, Type.Ghost)),
        PokemonSeries("Litten", R.drawable.littens, listOf(Type.Fire, Type.Dark)),
        PokemonSeries("Popplio", R.drawable.popplios, listOf(Type.Water, Type.Fairy)),
        PokemonSeries("Stufful", R.drawable.stuffuls, listOf(Type.Normal, Type.Fighting)),
        PokemonSeries("PichuAlola", R.drawable.pichualolas, listOf(Type.Electric,Type.Psychic))

    )

    val Galar = listOf(
        PokemonSeries("Sobble", R.drawable.sobbles, listOf(Type.Water)),
        PokemonSeries("Scorbunny", R.drawable.scorbunnys, listOf(Type.Fire)),
        PokemonSeries("Grookey", R.drawable.grookeys, listOf(Type.Grass)),
        PokemonSeries("Farfetch", R.drawable.farfetchs, listOf(Type.Flying, Type.Normal))
    )

    val regions = listOf(
        Region("KANTO", R.drawable.kanto, R.string.kantoRegion, Kanto),
        Region("JOHTO", R.drawable.johto, R.string.johtoRegion, Johto),
        Region("HOENN", R.drawable.hoenn, R.string.hoennRegion, Hoenn),
        Region("SINNOH", R.drawable.sinnoh, R.string.sinnohRegion, Sinnoh),
        Region("UNOVA", R.drawable.unova, R.string.unovaRegion, Unova),
        Region("KALOS", R.drawable.kalos, R.string.kalosRegion, Kalos),
        Region("ALOLA", R.drawable.alola, R.string.alolaRegion, Alola),
        Region("GALAR", R.drawable.galar,R.string.galarRegion, Galar)
    )
}