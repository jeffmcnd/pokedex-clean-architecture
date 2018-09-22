package xyz.mcnallydawes.pokedex.infrainmemory.source

import xyz.mcnallydawes.pokedex.Try
import xyz.mcnallydawes.pokedex.domain.source.SearchSource
import java.util.*

class HashMapSearchSource<T>(
        private val hashMap: HashMap<String, T>,
        private val getSearchStringFromValue: (T) -> String
) : SearchSource<T> {

    override fun search(query: String): Try<List<T>> = Try {
        val results: MutableList<T> = ArrayList()
        hashMap.values.toList().forEach { item ->
            val searchString = getSearchStringFromValue(item).toLowerCase(Locale.CANADA)
            if (searchString.startsWith(query.toLowerCase(Locale.CANADA))) {
                results.add(item)
            }
        }
        results
    }

}