package xyz.mcnallydawes.pokedex.domain.source

import xyz.mcnallydawes.pokedex.Try

interface ReadSource<T> {

    fun read(): Try<List<T>>

    fun read(id: String): Try<T?>

}