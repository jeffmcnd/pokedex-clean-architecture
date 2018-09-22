package xyz.mcnallydawes.pokedex.infrainmemory.source

import xyz.mcnallydawes.pokedex.Try
import xyz.mcnallydawes.pokedex.domain.source.CrudSource
import java.util.*
import kotlin.collections.HashMap

class HashMapCrudSource<T>(
        private val hashMap: HashMap<String, T>,
        private val getKeyFromValue: (T) -> String
) : CrudSource<T> {

    private val hashMapReadSource: HashMapReadSource<T> = HashMapReadSource(hashMap)

    override fun read(): Try<List<T>> = hashMapReadSource.read()

    override fun read(id: String): Try<T?> = hashMapReadSource.read(id)

    override fun create(item: T): Try<String> = Try {
        val key = UUID.randomUUID().toString()
        hashMap[key] = item
        key
    }

    override fun update(item: T): Try<String> = Try {
        val key = getKeyFromValue(item)
        hashMap[key] = item
        key
    }

    override fun delete(id: String) = Try {
        hashMap.remove(id)
        id
    }

}
