package xyz.mcnallydawes.pokedex.infraroom.mapper

import xyz.mcnallydawes.pokedex.domain.entity.Pokemon
import xyz.mcnallydawes.pokedex.infraroom.entity.RoomPokemon

class RoomPokemonToPokemon : (RoomPokemon) -> Pokemon {

    override fun invoke(pokemon: RoomPokemon) : Pokemon = with(pokemon) {
        Pokemon(id, name, types)
    }

}