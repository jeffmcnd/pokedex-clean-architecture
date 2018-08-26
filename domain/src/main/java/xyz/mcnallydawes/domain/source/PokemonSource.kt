package xyz.mcnallydawes.domain.source

import xyz.mcnallydawes.domain.Try
import xyz.mcnallydawes.domain.entity.Pokemon

interface PokemonSource {

    fun getAll(page: Int = 0, pageSize: Int = 25): Try<List<Pokemon>>

    fun getById(id: String, page: Int = 0, pageSize: Int = 25): Try<Pokemon?>

    fun getByNameStartingWith(name: String, page: Int = 0, pageSize: Int = 0): Try<List<Pokemon>>

}