package xyz.mcnallydawes.pokedex.common.adapter

import android.graphics.Color
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_pokemon.view.*
import xyz.mcnallydawes.pokedex.R
import xyz.mcnallydawes.pokedex.entity.Fire
import xyz.mcnallydawes.pokedex.entity.Grass
import xyz.mcnallydawes.pokedex.entity.Pokemon
import xyz.mcnallydawes.pokedex.entity.Water

class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(pokemon: Pokemon) = with(itemView) {
        nameTv.text = pokemon.name
        val type = pokemon.types.firstOrNull()
        if (type != null) {
            val color = when (type) {
                is Grass -> ResourcesCompat.getColor(resources, R.color.green, null)
                is Fire -> ResourcesCompat.getColor(resources, R.color.red, null)
                is Water -> ResourcesCompat.getColor(resources, R.color.blue, null)
            }
            setBackgroundColor(color)
        }
    }

    companion object {
        fun inflate(
                layoutInflater: LayoutInflater,
                root: ViewGroup?,
                attachToRoot: Boolean
        ): PokemonViewHolder = PokemonViewHolder(
                layoutInflater.inflate(R.layout.item_pokemon, root, attachToRoot)
        )
    }
}