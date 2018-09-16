package xyz.mcnallydawes.pokedex.searchpokemon

import xyz.mcnallydawes.pokedex.entity.Pokemon

data class SearchPokemonModel(var lastQuery: String, var pokemon: MutableList<Pokemon>)
