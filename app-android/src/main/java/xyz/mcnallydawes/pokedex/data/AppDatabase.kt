package xyz.mcnallydawes.pokedex.data

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import xyz.mcnallydawes.pokedex.common.converter.Converters
import xyz.mcnallydawes.pokedex.data.pokemon.PokemonDao
import xyz.mcnallydawes.pokedex.data.pokemon.RoomPokemon
import xyz.mcnallydawes.pokedex.entity.Fire
import xyz.mcnallydawes.pokedex.entity.Grass
import xyz.mcnallydawes.pokedex.entity.Pokemon
import xyz.mcnallydawes.pokedex.entity.Water
import java.util.concurrent.Executors

@Database(entities = [RoomPokemon::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context)
                            .also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "app.db"
                )
                        .fallbackToDestructiveMigration()
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                Executors.newSingleThreadScheduledExecutor().execute {
                                    getInstance(context).pokemonDao().add(
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
}
