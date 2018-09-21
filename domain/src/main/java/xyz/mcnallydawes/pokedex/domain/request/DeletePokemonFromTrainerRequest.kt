package xyz.mcnallydawes.pokedex.domain.request

data class DeletePokemonFromTrainerRequest(val trainerId: String, val pokemonId: String)