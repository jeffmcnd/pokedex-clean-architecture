package xyz.mcnallydawes.pokedex.infrainmemory

import org.koin.dsl.module.module
import xyz.mcnallydawes.pokedex.domain.entity.*
import xyz.mcnallydawes.pokedex.infrainmemory.source.*
import java.lang.IllegalStateException

private val pokemon = linkedMapOf(
        "1" to Pokemon("1", "Bulbasaur", setOf(Grass, Poison)),
        "2" to Pokemon("2", "Ivysaur", setOf(Grass, Poison)),
        "3" to Pokemon("3", "Venusaur", setOf(Grass, Poison)),
        "4" to Pokemon("4", "Charmander", setOf(Fire)),
        "5" to Pokemon("5", "Charmeleon", setOf(Fire)),
        "6" to Pokemon("6", "Charizard", setOf(Fire, Flying)),
        "7" to Pokemon("7", "Squirtle", setOf(Water)),
        "8" to Pokemon("8", "Wartortle", setOf(Water)),
        "9" to Pokemon("9", "Blastoise", setOf(Water)),
        "10" to Pokemon("10", "Caterpie", setOf(Bug)),
        "11" to Pokemon("11", "Metapod", setOf(Bug)),
        "12" to Pokemon("12", "Butterfree", setOf(Bug, Flying)),
        "13" to Pokemon("13", "Weedle", setOf(Bug, Poison)),
        "14" to Pokemon("14", "Kakuna", setOf(Bug, Poison)),
        "15" to Pokemon("15", "Beedrill", setOf(Bug, Poison)),
        "16" to Pokemon("16", "Pidgey", setOf(Flying, Normal)),
        "17" to Pokemon("17", "Pidgeotto", setOf(Flying, Normal)),
        "18" to Pokemon("18", "Pidgeot", setOf(Flying, Normal))
)

private val trainers = hashMapOf(
        "1" to Trainer("1", "Ash"),
        "2" to Trainer("2", "Misty"),
        "3" to Trainer("3", "Gary"),
        "4" to Trainer("4", "Brock"),
        "5" to Trainer("5", "Professor Oak"),
        "6" to Trainer("6", "Lt. Surge"),
        "7" to Trainer("7", "Koga")
)

val inMemoryModule = module {

    single(name="HashMapPokemonReadSource") { _ ->
        HashMapReadSource(pokemon)
    }

    single(name = "HashMapPokemonSearchSource") { _ ->
        val getNameFromPokemon: (Pokemon) -> String = { it.name }
        HashMapSearchSource(pokemon, getNameFromPokemon)
    }

    single(name = "HashMapTrainerCrudSource") { _ ->
        val getIdFromTrainer: (Trainer) -> String = {
            it.id ?: throw IllegalStateException("Attempted to retrieve null ID from Trainer")
        }
        HashMapCrudSource(trainers, getIdFromTrainer)
    }

    single(name = "HashMapTrainerSearchSource") { _ ->
        val getNameFromTrainer: (Trainer) -> String = { it.name }
        HashMapSearchSource(trainers, getNameFromTrainer)
    }

    single(name = "HashMapPokemonSource") {
        HashMapPokemonSource(get(), get(name = "HashMapPokemonSearchSource"))
    }

    single(name = "HashMapTrainerSource") {
        HashMapTrainerSource(get(), get(name = "HashMapTrainerSearchSource"))
    }

}
