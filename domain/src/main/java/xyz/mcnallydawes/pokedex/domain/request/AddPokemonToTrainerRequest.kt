package xyz.mcnallydawes.pokedex.domain.request

data class AddPokemonToTrainerRequest(val trainerId: String, val pokemonId: String)