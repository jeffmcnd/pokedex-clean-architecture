package xyz.mcnallydawes.pokedex.domain.source

import xyz.mcnallydawes.pokedex.Try

interface CrudSource<T> : ReadSource<T> {

    fun create(item: T): Try<String>

    fun update(item: T): Try<String>

    fun delete(id: String): Try<String>

}