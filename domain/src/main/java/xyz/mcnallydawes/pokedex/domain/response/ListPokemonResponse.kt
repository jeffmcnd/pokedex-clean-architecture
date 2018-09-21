package xyz.mcnallydawes.pokedex.domain.response

import xyz.mcnallydawes.pokedex.domain.entity.Pokemon

data class ListPokemonResponse(val pokemon: List<Pokemon>)