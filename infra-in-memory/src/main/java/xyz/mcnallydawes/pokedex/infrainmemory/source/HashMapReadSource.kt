package xyz.mcnallydawes.pokedex.infrainmemory.source

import xyz.mcnallydawes.pokedex.Try
import xyz.mcnallydawes.pokedex.domain.source.ReadSource

class HashMapReadSource<T>(private val hashMap: MutableMap<String, T>): ReadSource<T> {

    override fun read(): Try<List<T>> = Try {
        hashMap.values.toList()
    }

    override fun read(id: String): Try<T?> = Try {
        hashMap[id]
    }

}