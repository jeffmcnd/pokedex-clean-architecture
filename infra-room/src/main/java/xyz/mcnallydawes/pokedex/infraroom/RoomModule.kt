package xyz.mcnallydawes.pokedex.infraroom

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import xyz.mcnallydawes.pokedex.infraroom.entity.RoomPokemon
import xyz.mcnallydawes.pokedex.infraroom.source.RoomPokemonSource
import xyz.mcnallydawes.pokedex.domain.entity.Fire
import xyz.mcnallydawes.pokedex.domain.entity.Grass
import xyz.mcnallydawes.pokedex.domain.entity.Water
import xyz.mcnallydawes.pokedex.infraroom.mapper.RoomPokemonToPokemon
import java.util.concurrent.Executors

val roomModule = module {

    factory { RoomPokemonToPokemon() }

    single {
        Room.databaseBuilder(
                androidApplication(),
                AppDatabase::class.java, "app.db"
        )
                .fallbackToDestructiveMigration()
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        Executors.newSingleThreadScheduledExecutor().execute {
                            get<AppDatabase>().pokemonDao().add(
                                    RoomPokemon("1", "Bulbasaur", setOf(Grass)),
                                    RoomPokemon("2", "Ivysaur", setOf(Grass)),
                                    RoomPokemon("3", "Venusaur", setOf(Grass)),
                                    RoomPokemon("4", "Charmander", setOf(Fire)),
                                    RoomPokemon("5", "Charmeleon", setOf(Fire)),
                                    RoomPokemon("6", "Charizard", setOf(Fire)),
                                    RoomPokemon("7", "Squirtle", setOf(Water)),
                                    RoomPokemon("8", "Wartortle", setOf(Water)),
                                    RoomPokemon("9", "Blastoise", setOf(Water))
                            )
                        }
                    }
                })
                .build()
    }

    single { get<AppDatabase>().pokemonDao() }

    single { RoomPokemonSource(get(), get()) }

}
