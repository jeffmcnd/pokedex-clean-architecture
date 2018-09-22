package xyz.mcnallydawes.pokedex.infrainmemory.source

import xyz.mcnallydawes.pokedex.Try
import xyz.mcnallydawes.pokedex.domain.entity.Pokemon
import xyz.mcnallydawes.pokedex.domain.source.PokemonSource

class HashMapPokemonSource(
        private val hashMapReadSource: HashMapReadSource<Pokemon>,
        private val hashMapSearchSource: HashMapSearchSource<Pokemon>
) : PokemonSource {
    override fun read(): Try<List<Pokemon>> = hashMapReadSource.read()
    override fun read(id: String): Try<Pokemon?> = hashMapReadSource.read(id)
    override fun search(query: String): Try<List<Pokemon>> = hashMapSearchSource.search(query)
}