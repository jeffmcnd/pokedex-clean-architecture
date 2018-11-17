package xyz.mcnallydawes.pokedex.common.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.item_pokemon.view.*
import xyz.mcnallydawes.pokedex.common.util.TypeColorGenerator
import xyz.mcnallydawes.pokedex.common.adapter.PokemonClickListener
import xyz.mcnallydawes.pokedex.domain.entity.Pokemon

class PokemonViewHolder(
        itemView: View,
        private val typeColorGenerator: TypeColorGenerator
) : RecyclerView.ViewHolder(itemView) {

    fun bind(pokemon: Pokemon, listener: PokemonClickListener?) = with(itemView) {
        nameTv.text = pokemon.name

        backgroundLinearLayout.removeAllViews()

        val params = LinearLayout.LayoutParams(0, MATCH_PARENT, 1F)
        pokemon.types.forEach { type ->
            View(context).apply {
                setBackgroundColor(typeColorGenerator(resources, type))
                layoutParams = params
            }.also {
                backgroundLinearLayout.addView(it)
            }
        }

        setOnClickListener {
            listener?.invoke(pokemon)
        }
    }

}