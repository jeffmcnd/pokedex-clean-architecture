package xyz.mcnallydawes.pokedex.common.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import xyz.mcnallydawes.pokedex.common.inflater.PokemonInflater
import xyz.mcnallydawes.pokedex.common.viewholder.PokemonViewHolder
import xyz.mcnallydawes.pokedex.entity.Pokemon

typealias PokemonClickListener = (Pokemon) -> Unit

class PokemonAdapter(
        private val inflater: PokemonInflater,
        private val listener: PokemonClickListener? = null
) : RecyclerView.Adapter<PokemonViewHolder>() {

    private val pokemon: ArrayList<Pokemon> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
            inflater.inflate(parent, viewType)

    override fun getItemCount(): Int = pokemon.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemon[position], listener)
    }

    fun replace(pokemon: List<Pokemon>) {
        this.pokemon.clear()
        this.pokemon.addAll(pokemon)
        notifyDataSetChanged()
    }

}