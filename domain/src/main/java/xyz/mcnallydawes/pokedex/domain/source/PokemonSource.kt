package xyz.mcnallydawes.pokedex.domain.source

import xyz.mcnallydawes.pokedex.Try
import xyz.mcnallydawes.pokedex.domain.entity.Pokemon

interface PokemonSource {

    fun getAll(): Try<List<Pokemon>>

    fun getById(id: String): Try<Pokemon?>

    fun getByNameStartingWith(name: String): Try<List<Pokemon>>

}