package xyz.mcnallydawes.pokedex.infrainmemory.source

import xyz.mcnallydawes.pokedex.Try
import xyz.mcnallydawes.pokedex.domain.entity.Trainer
import xyz.mcnallydawes.pokedex.domain.source.TrainerSource

class HashMapTrainerSource(
        private val hashMapCrudSource: HashMapCrudSource<Trainer>,
        private val hashMapSearchSource: HashMapSearchSource<Trainer>
) : TrainerSource {
    override fun create(item: Trainer): Try<String> = hashMapCrudSource.create(item)
    override fun update(item: Trainer): Try<String> = hashMapCrudSource.update(item)
    override fun delete(id: String): Try<String> = hashMapCrudSource.delete(id)
    override fun read(): Try<List<Trainer>> = hashMapCrudSource.read()
    override fun read(id: String): Try<Trainer?> = hashMapCrudSource.read(id)
    override fun search(query: String): Try<List<Trainer>> = hashMapSearchSource.search(query)
}