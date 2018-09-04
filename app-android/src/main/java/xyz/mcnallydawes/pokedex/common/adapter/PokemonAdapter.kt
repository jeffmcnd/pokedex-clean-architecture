package xyz.mcnallydawes.pokedex.common.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import xyz.mcnallydawes.pokedex.entity.Pokemon

class PokemonAdapter(
        private val layoutInflater: LayoutInflater
) : RecyclerView.Adapter<PokemonViewHolder>() {

    private val pokemon: ArrayList<Pokemon> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
            PokemonViewHolder.inflate(layoutInflater, parent, false)

    override fun getItemCount(): Int = pokemon.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemon[position])
    }

    fun addPokemon(pokemon: List<Pokemon>) {
        this.pokemon.addAll(pokemon)
        notifyDataSetChanged()
    }

}