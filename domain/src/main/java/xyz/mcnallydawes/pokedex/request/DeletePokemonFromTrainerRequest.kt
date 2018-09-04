package xyz.mcnallydawes.pokedex.request

data class DeletePokemonFromTrainerRequest(val trainerId: String, val pokemonId: String)