package xyz.mcnallydawes.pokedex.infrainmemory.source

import xyz.mcnallydawes.pokedex.Try
import xyz.mcnallydawes.pokedex.domain.entity.Trainer
import xyz.mcnallydawes.pokedex.domain.source.TrainerSource
import java.util.*

class HashMapTrainerSource : TrainerSource {

    private val idsToTrainers: LinkedHashMap<String, Trainer> = linkedMapOf(
            "1" to Trainer("1", "Ash"),
            "2" to Trainer("2", "Misty"),
            "3" to Trainer("3", "Brock"),
            "4" to Trainer("4", "Gary")
    )

    override fun save(trainer: Trainer): Try<String> = Try {
        val id = trainer.id ?: UUID.randomUUID().toString()
        trainer.copy(id = id)
        idsToTrainers[id] = trainer
        id
    }

    override fun delete(trainer: Trainer): Try<String> = Try {
        val id = trainer.id ?: throw Throwable("Trainer's ID was null, cannot delete.")
        idsToTrainers.remove(id)
        id
    }

    override fun getAll(): Try<List<Trainer>> = Try {
        idsToTrainers.values.toList()
    }

    override fun getById(id: String): Try<Trainer?> = Try {
        idsToTrainers[id]
    }

    override fun getByName(name: String): Try<Trainer?> = Try {
        idsToTrainers.values.toList().firstOrNull { trainer ->
            trainer.name.toLowerCase(Locale.CANADA) == name.toLowerCase(Locale.CANADA)
        }
    }

    override fun getByNameStartingWith(name: String): Try<List<Trainer>> = Try {
        idsToTrainers.values.toList().filter { trainer ->
            trainer.name.toLowerCase(Locale.CANADA).startsWith(name.toLowerCase(Locale.CANADA))
        }
    }

}