package xyz.mcnallydawes.pokedex.common.viewholder

import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_pokemon.view.*
import xyz.mcnallydawes.pokedex.R
import xyz.mcnallydawes.pokedex.common.adapter.PokemonClickListener
import xyz.mcnallydawes.pokedex.domain.entity.Fire
import xyz.mcnallydawes.pokedex.domain.entity.Grass
import xyz.mcnallydawes.pokedex.domain.entity.Pokemon
import xyz.mcnallydawes.pokedex.domain.entity.Water

class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(pokemon: Pokemon, listener: PokemonClickListener?) = with(itemView) {
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
        setOnClickListener {
            listener?.invoke(pokemon)
        }
    }

}