package xyz.mcnallydawes.pokedex.common.inflater

import android.view.LayoutInflater
import android.view.ViewGroup
import xyz.mcnallydawes.pokedex.R
import xyz.mcnallydawes.pokedex.common.util.TypeColorGenerator
import xyz.mcnallydawes.pokedex.common.viewholder.PokemonViewHolder

class PokemonInflater(
        private val layoutInflater: LayoutInflater,
        private val typeColorGenerator: TypeColorGenerator
) {

    fun inflate(parent: ViewGroup, viewType: Int) =
            PokemonViewHolder(
                    layoutInflater.inflate(R.layout.item_pokemon, parent, false),
                    typeColorGenerator
            )

}