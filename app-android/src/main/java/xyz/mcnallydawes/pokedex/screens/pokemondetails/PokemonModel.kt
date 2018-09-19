package xyz.mcnallydawes.pokedex.screens.pokemondetails

import xyz.mcnallydawes.pokedex.entity.Pokemon

data class PokemonModel(val isLoading: Boolean = false, val pokemon: Pokemon? = null)