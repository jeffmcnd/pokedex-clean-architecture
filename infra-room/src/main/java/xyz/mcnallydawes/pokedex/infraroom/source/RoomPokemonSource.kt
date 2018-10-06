package xyz.mcnallydawes.pokedex.infraroom.source

import xyz.mcnallydawes.pokedex.Try
import xyz.mcnallydawes.pokedex.domain.entity.Pokemon
import xyz.mcnallydawes.pokedex.infraroom.dao.PokemonDao
import xyz.mcnallydawes.pokedex.infraroom.mapper.RoomPokemonToPokemon
import xyz.mcnallydawes.pokedex.domain.source.PokemonSource

class RoomPokemonSource(
        private val dao: PokemonDao,
        private val roomToPokemon: RoomPokemonToPokemon
) : PokemonSource {

    override fun read(): Try<List<Pokemon>> = Try {
        dao.read().map(roomToPokemon)
    }

    override fun read(id: String): Try<Pokemon?> = Try {
        val pokemon = dao.read(id)
        if (pokemon == null) {
            null
        } else {
            roomToPokemon.invoke(pokemon)
        }
    }

    override fun search(query: String): Try<List<Pokemon>> = Try {
        dao.search("%$query%").map(roomToPokemon)
    }

}