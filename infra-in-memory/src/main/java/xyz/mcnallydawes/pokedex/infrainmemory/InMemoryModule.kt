package xyz.mcnallydawes.pokedex.infrainmemory

import org.koin.dsl.module.module
import xyz.mcnallydawes.pokedex.infrainmemory.source.HashMapPokemonSource
import xyz.mcnallydawes.pokedex.infrainmemory.source.HashMapTrainerSource

val inMemoryModule = module {

    single { HashMapPokemonSource() }

    single { HashMapTrainerSource() }

}
