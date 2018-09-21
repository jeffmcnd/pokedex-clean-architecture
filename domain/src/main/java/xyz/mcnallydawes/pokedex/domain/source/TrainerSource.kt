package xyz.mcnallydawes.pokedex.domain.source

import xyz.mcnallydawes.pokedex.Try
import xyz.mcnallydawes.pokedex.domain.entity.Trainer

interface TrainerSource {

    fun save(trainer: Trainer): Try<String>

    fun delete(trainer: Trainer): Try<String>

    fun getAll(): Try<List<Trainer>>

    fun getById(id: String): Try<Trainer?>

    fun getByName(name: String): Try<Trainer?>

    fun getByNameStartingWith(name: String): Try<List<Trainer>>

}