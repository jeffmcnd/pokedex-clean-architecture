package xyz.mcnallydawes.pokedex.data.pokemon

import xyz.mcnallydawes.pokedex.Try
import xyz.mcnallydawes.pokedex.entity.Grass
import xyz.mcnallydawes.pokedex.entity.Pokemon
import xyz.mcnallydawes.pokedex.source.PokemonSource
import java.util.*

class ListPokemonSource : PokemonSource {

    private val pokemon = arrayListOf(
            Pokemon("1", "Bulbasaur", setOf(Grass)),
            Pokemon("2", "Ivysaur", setOf(Grass)),
            Pokemon("3", "Venusaur", setOf(Grass))
    )

    override fun getAll(): Try<List<Pokemon>> = Try {
        pokemon
    }

    override fun getById(id: String): Try<Pokemon?> = Try {
        pokemon.firstOrNull { pokemon ->
            id == pokemon.id
        }
    }

    override fun getByNameStartingWith(name: String): Try<List<Pokemon>> = Try {
        pokemon.filter { pokemon ->
            pokemon.name.startsWith(name.toLowerCase(Locale.CANADA))
        }
    }

}