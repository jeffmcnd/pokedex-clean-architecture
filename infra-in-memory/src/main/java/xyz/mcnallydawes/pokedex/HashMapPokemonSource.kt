package xyz.mcnallydawes.pokedex

import xyz.mcnallydawes.pokedex.entity.Grass
import xyz.mcnallydawes.pokedex.entity.Pokemon
import xyz.mcnallydawes.pokedex.source.PokemonSource
import java.util.*

class HashMapPokemonSource : PokemonSource {

    private val idsToPokemon: HashMap<String, Pokemon> = hashMapOf(
            "1" to Pokemon("1", "Bulbasaur", setOf(Grass)),
            "2" to Pokemon("2", "Ivysaur", setOf(Grass)),
            "3" to Pokemon("3", "Venusaur", setOf(Grass))
    )

    override fun getAll(): Try<List<Pokemon>> = Try {
        idsToPokemon.values.toList()
    }

    override fun getById(id: String): Try<Pokemon?> = Try {
        idsToPokemon[id]
    }

    override fun getByNameStartingWith(name: String): Try<List<Pokemon>> = Try {
        idsToPokemon.filter { entry ->
            entry.value.name.startsWith(name.toLowerCase(Locale.CANADA))
        }.values.toList()
    }

}