package xyz.mcnallydawes.pokedex.domain.source

import xyz.mcnallydawes.pokedex.Try

interface SearchSource<T> {

    fun search(query: String) : Try<List<T>>

}