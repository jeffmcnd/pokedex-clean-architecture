package xyz.mcnallydawes.pokedex.request

data class AddPokemonToTrainerRequest(val trainerId: String, val pokemonId: String)