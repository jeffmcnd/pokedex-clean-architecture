package xyz.mcnallydawes.domain.source

import xyz.mcnallydawes.domain.Try
import xyz.mcnallydawes.domain.entity.Trainer

interface TrainerSource {

    fun save(trainer: Trainer): Try<String>

    fun delete(trainer: Trainer): Try<String>

    fun getAll(page: Int = 0, pageSize: Int = 25): Try<List<Trainer>>

    fun getById(id: String): Try<Trainer?>

    fun getByNameStartingWith(name: String, page: Int = 0, pageSize: Int = 25): Try<List<Trainer>>

}
