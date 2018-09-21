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

    override fun getAll(): Try<List<Pokemon>> = Try {
        dao.getAll().map(roomToPokemon)
    }

    override fun getById(id: String): Try<Pokemon?> = Try {
        val pokemon = dao.getById(id)
        if (pokemon == null) {
            null
        } else {
            roomToPokemon.invoke(pokemon)
        }
    }

    override fun getByNameStartingWith(name: String): Try<List<Pokemon>> = Try {
        dao.getByNameStartingWith("%$name%").map(roomToPokemon)
    }

}