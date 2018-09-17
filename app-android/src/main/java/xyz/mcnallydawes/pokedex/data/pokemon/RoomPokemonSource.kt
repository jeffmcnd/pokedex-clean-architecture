package xyz.mcnallydawes.pokedex.data.pokemon

import xyz.mcnallydawes.pokedex.Try
import xyz.mcnallydawes.pokedex.entity.Pokemon
import xyz.mcnallydawes.pokedex.source.PokemonSource

class RoomPokemonSource(
        private val dao: PokemonDao,
        private val mapper: PokemonMapper
) : PokemonSource {

    override fun getAll(): Try<List<Pokemon>> = Try {
        dao.getAll().map { pokemon ->
            mapper.fromRoom(pokemon)
        }
    }

    override fun getById(id: String): Try<Pokemon?> = Try {
        val pokemon = dao.getById(id)
        if (pokemon == null) {
            null
        } else {
            mapper.fromRoom(pokemon)
        }
    }

    override fun getByNameStartingWith(name: String): Try<List<Pokemon>> = Try {
        dao.getByNameStartingWith("%$name%").map { pokemon ->
            mapper.fromRoom(pokemon)
        }
    }

}