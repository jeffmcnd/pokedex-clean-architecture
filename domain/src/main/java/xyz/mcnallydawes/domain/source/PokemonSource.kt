package xyz.mcnallydawes.domain.source

import xyz.mcnallydawes.domain.Try
import xyz.mcnallydawes.domain.entity.Pokemon

interface PokemonSource {

    fun getAll(): Try<List<Pokemon>>

    fun getById(id: String): Try<Pokemon?>

    fun getByNameStartingWith(name: String): Try<List<Pokemon>>

}