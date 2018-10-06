package xyz.mcnallydawes.pokedex.common.viewholder

import android.content.res.Resources
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.item_pokemon.view.*
import xyz.mcnallydawes.pokedex.R
import xyz.mcnallydawes.pokedex.common.adapter.PokemonClickListener
import xyz.mcnallydawes.pokedex.domain.entity.*

class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(pokemon: Pokemon, listener: PokemonClickListener?) = with(itemView) {
        nameTv.text = pokemon.name

        backgroundLinearLayout.removeAllViews()

        val params = LinearLayout.LayoutParams(0, MATCH_PARENT, 1F)
        pokemon.types.forEach { type ->
            View(context).apply {
                setBackgroundColor(getColorForType(resources, type))
                layoutParams = params
            }.also {
                backgroundLinearLayout.addView(it)
            }
        }

        setOnClickListener {
            listener?.invoke(pokemon)
        }
    }

    private fun getColorForType(resources: Resources, type: Type) = when (type) {
        Grass -> ResourcesCompat.getColor(resources, R.color.grassColor, null)
        Fire -> ResourcesCompat.getColor(resources, R.color.fireColor, null)
        Water -> ResourcesCompat.getColor(resources, R.color.waterColor, null)
        Bug -> ResourcesCompat.getColor(resources, R.color.bugColor, null)
        Normal -> ResourcesCompat.getColor(resources, R.color.normalColor, null)
        Flying -> ResourcesCompat.getColor(resources, R.color.flyingColor, null)
        Poison -> ResourcesCompat.getColor(resources, R.color.poisonColor, null)
    }

}