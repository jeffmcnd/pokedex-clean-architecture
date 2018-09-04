package xyz.mcnallydawes.pokedex.data.pokemon

import xyz.mcnallydawes.pokedex.entity.Pokemon

class PokemonMapper {

    fun toRoom(pokemon: Pokemon) : RoomPokemon = with(pokemon) {
        RoomPokemon(pokemon.id, pokemon.name, pokemon.types)
    }

    fun fromRoom(pokemon: RoomPokemon) : Pokemon = with(pokemon) {
        Pokemon(pokemon.id, pokemon.name, pokemon.types)
    }

}